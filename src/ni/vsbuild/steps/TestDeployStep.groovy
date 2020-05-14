package ni.vsbuild.steps

import ni.vsbuild.BuildConfiguration

class TestDeployStep extends LvStep {

   def feed
   def package
   def version

   TestDeployStep(script, mapStep, lvVersion) {
      super(script, mapStep, lvVersion)
      this.feed = mapStep.get('feed')
	  this.package = mapStep.get('package')
	  this.version = mapStep.get('version')
   }

   void executeStep(BuildConfiguration configuration) {
      script.testDeploy(feed,package,version,lvVersion)
   }
}
