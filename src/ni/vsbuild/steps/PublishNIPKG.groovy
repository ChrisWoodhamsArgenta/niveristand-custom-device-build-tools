package ni.vsbuild.steps

import ni.vsbuild.BuildConfiguration

class PublishNIPKG extends LvStep {

   def variable
   def path

   PublishNIPKG(script, mapStep, lvVersion) {
      super(script, mapStep, lvVersion)
      this.variable = mapStep.get('variable')
	  this.path = mapStep.get('path')
   }

   void executeStep(BuildConfiguration configuration) {
      script.publishNIPKG(variable, path)
   }
}
