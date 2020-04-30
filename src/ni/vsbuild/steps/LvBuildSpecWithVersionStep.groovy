package ni.vsbuild.steps

class LvBuildSpecWithVersionStep extends LvBuildStep {

   def target
   def spec

   LvBuildSpecWithVersionStep(script, mapStep, lvVersion) {
      super(script, mapStep, lvVersion)
      this.target = mapStep.get('target')
      this.spec = mapStep.get('build_spec')
   }

   void executeBuildStep(String projectPath) {
      script.lvBuildSpecWithVersion(projectPath, target, spec, lvVersion)
   }
}
