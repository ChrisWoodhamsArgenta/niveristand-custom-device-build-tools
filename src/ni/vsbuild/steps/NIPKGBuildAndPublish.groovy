package ni.vsbuild.steps

import ni.vsbuild.BuildConfiguration

class NIPKGBuildAndPublish extends LvStep {

   def feed
   def pkg_name

   NIPKGBuildAndPublish(script, mapStep, lvVersion) {
      super(script, mapStep, lvVersion)
      this.feed = mapStep.get('feed')
	  this.pkg_name = mapStep.get('package')
   }

   void executeStep(BuildConfiguration configuration) {
      script.nipkgBuildAndPublish(feed, pkg_name)
   }
}
