package ni.vsbuild.steps

import ni.vsbuild.BuildConfiguration

class TestDeployStep extends LvStep {

   def feed
   def pkg_name
   def exe_path

   TestDeployStep(script, mapStep, lvVersion) {
      super(script, mapStep, lvVersion)
      this.feed = mapStep.get('feed')
	  this.pkg_name = mapStep.get('package')
	  this.exe_path = mapStep.get('exe_path')
   }

   void executeStep(BuildConfiguration configuration) {
      script.testDeploy(feed,pkg_name,exe_path,lvVersion)
   }
}
