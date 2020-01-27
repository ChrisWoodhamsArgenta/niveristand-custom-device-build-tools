package ni.vsbuild.steps

import ni.vsbuild.BuildConfiguration

class LvRunVIAnalyzerStep extends LvStep {

   def cfg_path

   LvRunVIAnalyzerStep(script, mapStep, lvVersion) {
      super(script, mapStep, lvVersion)
      this.cfg_path = mapStep.get('cfg_path')
   }

   void executeStep(BuildConfiguration configuration) {
      script.lvRunVIAnalyzer(cfg_path, lvVersion)
   }
}
