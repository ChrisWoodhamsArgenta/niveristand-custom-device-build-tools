package ni.vsbuild.steps

import ni.vsbuild.BuildConfiguration

class WriteEnvVariableToFileStep extends LvStep {

   def variable
   def path
   def variable_value

   WriteEnvVariableToFileStep(script, mapStep, lvVersion) {
      super(script, mapStep, lvVersion)
      this.variable = mapStep.get('variable')
	  this.path = mapStep.get('path')
	  this.variable_value = script.env.GIT_BRANCH
   }

   void executeStep(BuildConfiguration configuration) {
      script.writeEnvVariableToFile(variable, variable_value, path)
   }
}
