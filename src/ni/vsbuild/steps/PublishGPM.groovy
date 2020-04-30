package ni.vsbuild.steps

import ni.vsbuild.BuildConfiguration

class PublishGPM extends LvStep {

   def registry
   def pkg_name

   PublishNIPKG(script, mapStep, lvVersion) {
      super(script, mapStep, lvVersion)
      this.registry = mapStep.get('registry')
	  this.pkg_name = mapStep.get('package')
   }

   void executeStep(BuildConfiguration configuration) {
      script.publishGPM(registry, pkg_name)
   }
}
