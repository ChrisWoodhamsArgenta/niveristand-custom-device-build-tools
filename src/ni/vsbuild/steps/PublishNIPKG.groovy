package ni.vsbuild.steps

import ni.vsbuild.BuildConfiguration

class PublishNIPKG extends LvStep {

   def feed
   def pkg_name

   PublishNIPKG(script, mapStep, lvVersion) {
      super(script, mapStep, lvVersion)
      this.feed = mapStep.get('feed')
	  this.package = mapStep.get('package')
   }

   void executeStep(BuildConfiguration configuration) {
      script.publishNIPKG(feed, pkg_name)
   }
}
