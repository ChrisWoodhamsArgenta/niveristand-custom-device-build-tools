package ni.vsbuild.steps

import ni.vsbuild.BuildConfiguration

class NIPKGBuildAndPublish extends LvStep {

   def feed
   def pkg_name
   def pkg_version
   def dependencies
   def package_dst_path
   def package_src_path
   

   NIPKGBuildAndPublish(script, mapStep, lvVersion) {
      super(script, mapStep, lvVersion)
	  
	  //get version
	  def baseVersion = script.env.BRANCH_NAME.split("[-/]")[1]
      def versionPartCount = baseVersion.tokenize(".").size()

      def versionPartsToDisplay = 3
      for(versionPartCount; versionPartCount < versionPartsToDisplay; versionPartCount++) {
         baseVersion = "${baseVersion}.0"
      }
	  
      def fullVersion = "${baseVersion}-${script.currentBuild.number}"
	  
	  
	  // rest
      this.feed = mapStep.get('feed')
	  this.pkg_name = mapStep.get('package')
	  this.pkg_version = fullVersion
	  this.dependencies = mapStep.get('dependencies')
	  this.package_dst_path = mapStep.get('destination')
	  this.package_src_path = mapStep.get('source')
   }

   void executeStep(BuildConfiguration configuration) {
      script.nipkgBuildAndPublish(feed, pkg_name,pkg_version,dependencies,package_dst_path,package_src_path)
   }
   
    private def getBaseVersionP() {
      def baseVersion = script.env.BRANCH_NAME.split("[-/]")[1]
      def versionPartCount = baseVersion.tokenize(".").size()

      def versionPartsToDisplay = 3
      for(versionPartCount; versionPartCount < versionPartsToDisplay; versionPartCount++) {
         baseVersion = "${baseVersion}.0"
      }

      return baseVersion
   }

   private def getFullVersionP() {
      def baseVersion = getBaseVersionP()
      def fullVersion = "${baseVersion}.${script.currentBuild.number}"

      return fullVersion
   }
   private def getFullBuildVersionP() {
      def baseVersion = getBaseVersionP()
      def fullVersion = "${baseVersion}-${script.currentBuild.number}"

      return fullVersion
   }
   
}
