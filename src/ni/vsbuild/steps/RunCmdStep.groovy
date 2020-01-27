package ni.vsbuild.steps

import ni.vsbuild.BuildConfiguration

class RunCmdStep extends LvStep {

   def cmd

   RunCmdStep(script, mapStep, lvVersion) {
      super(script, mapStep, lvVersion)
      this.cmd = mapStep.get('cmd')
   }

   void executeStep(BuildConfiguration configuration) {
      script.runCmd(cmd, lvVersion)
   }
}
