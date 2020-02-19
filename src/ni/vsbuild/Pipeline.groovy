package ni.vsbuild

import ni.vsbuild.stages.*

class Pipeline implements Serializable {


   private static final String MANIFEST_FILE = 'Built/installer/manifest.json'
   
   String configurationFile
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

      // The plan is to enable automatic merging from master to
      // release or hotfix branch packages and not build packages
      // for any other branches, including master. The version must
      // be appended to the release or hotfix branch name after a
      // dash (-) or slash (/).
      def shouldBuildPackage() {
         return buildConfiguration.packageInfo &&
            (script.env.BRANCH_NAME.startsWith("release") ||
            script.env.BRANCH_NAME.startsWith("hotfix"))
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
	  setConfigFile()
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
               setup(lvVersion)
			   
				//script.echo 'test, displaying tigger cause'
			   	// started by commit
				//script.echo "${currentBuild.getBuildCauses('jenkins.branch.BranchEventCause')}"
				// started by time
				//script.echo "${currentBuild.getBuildCauses('hudson.triggers.TimerTrigger$TimerTriggerCause')}"
				// started by user
				//script.echo "${currentBuild.getBuildCauses('hudson.model.Cause$UserIdCause')}"

               def configuration = BuildConfiguration.load(script, configurationFile, lvVersion)
               configuration.printInformation(script)

               def builder = new Builder(script, configuration, lvVersion, MANIFEST_FILE)
               this.stages = builder.buildPipeline()

               executeStages()
            }
         }
      }

      script.parallel builders
	   // DW: we don't run things in paralle yet, and validation cause build not to finish when another build starts at the current agent. Then validateBuild() needs to wait untill agent is free. It might need to be uncomented and investigated in the future
      //validateBuild()
   }
   
   	private void setConfigFile() {
		if(doesFileExist('build.json')){
         configurationFile =  'build.json'
		}else{
		 configurationFile =  'build_config/default.json'
		}
		script.echo 'configFilePath:'
		script.echo configurationFile
	}
	
	def doesFileExist(filePath) {
		def branchName = script.env.BRANCH_NAME
		script.echo branchName
	//	def workspace2 = script.manager.build.getEnvVars()["WORKSPACE"]
	//	script.echo workspace2
		def workspacePath = script.env.WORKSPACE
		def fullPath = "$script.env.WORKSPACE\$filePath"
		script.echo workspacePath
		script.echo fullPath
		def folder = new File(fullPath)
		return folder.exists()
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

   private void setup(lvVersion) {
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
         script.buildSetup(lvVersion)

         // Write a manifest
         script.echo "Writing manifest to $MANIFEST_FILE"
         script.writeJSON file: MANIFEST_FILE, json: manifest, pretty: 3
      }
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
