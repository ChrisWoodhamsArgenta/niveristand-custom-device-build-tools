package ni.vsbuild

import ni.vsbuild.stages.*

class Pipeline implements Serializable {

   private static final String JSON_FILE = 'build.json'

   private static final String MANIFEST_FILE = 'Built/installer/manifest.json'

   def script
   PipelineInformation pipelineInformation
   def stages = []

   static class Builder implements Serializable {

      def script
      BuildConfiguration buildConfiguration
      String lvVersion
      String manifestFile
      def stages = []

      Builder(def script, BuildConfiguration buildConfiguration, String lvVersion, String manifestFile) {
         this.script = script
         this.buildConfiguration = buildConfiguration
         this.lvVersion = lvVersion
         this.manifestFile = manifestFile
      }

      def withCodegenStage() {
         stages << new Codegen(script, buildConfiguration, lvVersion)
      }

      def withBuildStage() {
         stages << new Build(script, buildConfiguration, lvVersion)
      }

      def withTestStage() {
         stages << new Test(script, buildConfiguration, lvVersion)
      }

      def withPackageStage() {
         stages << new Package(script, buildConfiguration, lvVersion)
      }

      def withArchiveStage() {
         stages << new Archive(script, buildConfiguration, lvVersion, manifestFile)
      }
	  
	  def withPublishStage() {
         stages << new Publish(script, buildConfiguration, lvVersion)
	  }
		 
	  def withDeployStage() {
         stages << new Deploy(script, buildConfiguration, lvVersion)
      }
	  
	  def withTestDeployStage() {
         stages << new TestDeploy(script, buildConfiguration, lvVersion)
      }

      // The plan is to enable automatic merging from master to
      // release or hotfix branch packages and not build packages
      // for any other branches, including master. The version must
      // be appended to the release or hotfix branch name after a
      // dash (-) or slash (/).
      def shouldBuildPackage() {
         return buildConfiguration.packageInfo
			//&&
            //(script.env.BRANCH_NAME.startsWith("release") ||
            //script.env.BRANCH_NAME.startsWith("hotfix"))
      }

      def buildPipeline() {
         if(buildConfiguration.codegen || buildConfiguration.projects) {
            withCodegenStage()
         }

         if(buildConfiguration.build) {
            withBuildStage()
         }

         if(buildConfiguration.test){
            withTestStage()
         }

         if(shouldBuildPackage()) {
            withPackageStage()
         }

         if(buildConfiguration.archive) {
            withArchiveStage()
         }
		 
		 if(buildConfiguration.publish) {
            withPublishStage()
         }
		 
		 if(buildConfiguration.deploy) {
            withDeployStage()
         }
		 
		// if(buildConfiguration.testdeploy) {
         //   withTestDeployStage()
         //}

         return stages
      }
	  def buildTestDeployPipeline() {
	
		if(buildConfiguration.testdeploy) {
            withTestDeployStage()
         }

         return stages
      }
   }

   Pipeline(script, PipelineInformation pipelineInformation) {
      this.script = script
      this.pipelineInformation = pipelineInformation
   }

   void execute() {

      // build dependencies before starting this pipeline
      script.buildDependencies(pipelineInformation)

      def builders = [:]

      for(String version : pipelineInformation.lvVersions) {

         // need to bind the variable before the closure - can't do 'for (version in lvVersions)'
         def lvVersion = version

         String nodeLabel = lvVersion
         if (pipelineInformation.nodeLabel?.trim()) {
            nodeLabel = "$nodeLabel && ${pipelineInformation.nodeLabel}"
         }

         builders[lvVersion] = {
            script.node(nodeLabel) {
               setup(lvVersion,nodeLabel)

               def configuration = BuildConfiguration.load(script, JSON_FILE, lvVersion)
               configuration.printInformation(script)

               def builder = new Builder(script, configuration, lvVersion, MANIFEST_FILE)
               this.stages = builder.buildPipeline()
				
				//DW temporary storage for version
				updateVersionFile()
				updateBuildNumberFile()
               executeStages()
            }
			def deployNodeLabel = 'WinDeployNIPKG'
			def builderDeploy = new Builder(script, configuration, lvVersion, MANIFEST_FILE)
			this.stages = builderDeploy.buildTestDeployPipeline()
			script.node(deployNodeLabel) {
				//if(this.buildConfiguration.testdeploy) {
					setup(lvVersion,deployNodeLabel)
				//}
				executeStages()
			}
         }
      }

      script.parallel builders
	   // DW: we don't run things in paralle yet, and validation cause build not to finish when another build starts at the current agent. Then validateBuild() needs to wait untill agent is free. It might need to be uncomented and investigated in the future
      //validateBuild()
   }

   protected void executeStages() {
      for (Stage stage : stages) {
         try {
            stage.execute()
         } catch (err) {
            script.failBuild(err.getMessage())
         }
      }
   }

   private void setup(lvVersion,nodeLabel) {
      def manifest = script.readJSON text: '{}'

      script.stage("Checkout_$lvVersion") {
         script.deleteDir()
         script.echo 'Attempting to get source from repo.'
         script.timeout(time: 30, unit: 'MINUTES'){
            manifest['scm'] = script.checkout(script.scm)
         }
      }
      script.stage("Setup_$lvVersion") {
         script.cloneBuildTools()
		 def branchName = script.env.BRANCH_NAME
		 script.echo branchName
		 def workspacePath = script.env.WORKSPACE
		 script.echo workspacePath
		 def trigger = 'user_or_commit'
		 
		 // is started by timer by time
		def trigger_cause = "${script.currentBuild.getBuildCauses('hudson.triggers.TimerTrigger$TimerTriggerCause')}"
		script.echo trigger_cause
		
		if(trigger_cause=='[]')
		{
			
		}else
		{
			trigger = 'timer'
		}
		 
         script.buildSetup(lvVersion, workspacePath, branchName,trigger,nodeLabel)

         // Write a manifest
         script.echo "Writing manifest to $MANIFEST_FILE"
         script.writeJSON file: MANIFEST_FILE, json: manifest, pretty: 3
      }
   }
   
    private def getBaseVersion() {
      def baseVersion = script.env.BRANCH_NAME.split("[-/]")[1]
      def versionPartCount = baseVersion.tokenize(".").size()

      def versionPartsToDisplay = 3
      for(versionPartCount; versionPartCount < versionPartsToDisplay; versionPartCount++) {
         baseVersion = "${baseVersion}.0"
      }

      return baseVersion
   }
   
    private def hasVersion1() {
      def baseVersion = script.env.BRANCH_NAME.split("[-/]")[1]
      def versionPartCount = baseVersion.tokenize(".").size()

      def versionPartsToDisplay = 3
	  
	  def branchHasVersion = 0
	  if(versionPartCount == versionPartsToDisplay) branchHasVersion=1

      return baseVersion
   }
   
    private def hasVersion() {
	  return (script.env.BRANCH_NAME.startsWith("release") || script.env.BRANCH_NAME.startsWith("hotfix"))
   }
        
   
    private void updateVersionFile() {
	  def baseVersion = '0.2.0'
      if(hasVersion()) {
         baseVersion = getBaseVersion()
      }
	  def csvVersion = baseVersion.replaceAll('\\.',',')
	  def workspace_path = script.env.WORKSPACE

      script.writeFile file: "$workspace_path\\version.txt", text: csvVersion
   }
   
    private void updateBuildNumberFile() {
	  def workspace_path = script.env.WORKSPACE
	  def build_num = script.env.BUILD_NUMBER

      script.writeFile file: "$workspace_path\\buildnumber.txt", text: build_num
   }

   // This method is here to catch builds with issue 50:
   // https://github.com/ni/niveristand-custom-device-build-tools/issues/50
   // If this issue is encountered, the build will still show success even
   // though an export for the desired version is not actually created.
   // We should fail the build instead of returning false success.
   private void validateBuild() {      
      String nodeLabel = ''
      if (pipelineInformation.nodeLabel?.trim()) {
         nodeLabel = pipelineInformation.nodeLabel
      }

      script.node(nodeLabel) {
         script.stage("Validation") {
            script.echo("Validating build output.")
            def component = script.getComponentParts()['repo']
            def exportDir = script.env."${component}_DEP_DIR"
            pipelineInformation.lvVersions.each { version ->
               if(!script.fileExists("$exportDir\\$version")) {
                  script.failBuild("Failed to build version $version. See issue: https://github.com/ni/niveristand-custom-device-build-tools/issues/50")
               }
            }
         }
      }
   }
}
