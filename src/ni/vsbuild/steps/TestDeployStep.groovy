package ni.vsbuild.steps

import ni.vsbuild.BuildConfiguration

class TestDeployStep extends LvStep {

   def feed
   def package
   def exe_path

   TestDeployStep(script, mapStep, lvVersion) {
      super(script, mapStep, lvVersion)
      this.feed = mapStep.get('feed')
	  this.package = mapStep.get('package')
	  this.exe_path = mapStep.get('exe_path')
   }

   void executeStep(BuildConfiguration configuration) {
      script.testDeploy(feed,package,exe_path,lvVersion)
   }
}
