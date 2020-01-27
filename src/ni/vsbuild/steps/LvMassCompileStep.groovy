package ni.vsbuild.steps

import ni.vsbuild.BuildConfiguration

class LvMassCompileStep extends LvStep {

   def dir_to_mc

   LvMassCompileStep(script, mapStep, lvVersion) {
      super(script, mapStep, lvVersion)
      this.dir_to_mc = mapStep.get('dir_to_mc')
   }

   void executeStep(BuildConfiguration configuration) {
      script.lvMassCompile(dir_to_mc, lvVersion)
   }
}
