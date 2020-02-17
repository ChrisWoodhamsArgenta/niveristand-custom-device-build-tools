package ni.vsbuild.steps

import ni.vsbuild.BuildConfiguration

class WriteEnvVariableToFileStep extends LvStep {

   def variable
   def path

   WriteEnvVariableToFileStep(script, mapStep, lvVersion) {
      super(script, mapStep, lvVersion)
      this.variable = mapStep.get('variable')
	  this.path = mapStep.get('path')
   }

   void executeStep(BuildConfiguration configuration) {
      script.writeEnvVariableToFile(variable, path)
   }
}
