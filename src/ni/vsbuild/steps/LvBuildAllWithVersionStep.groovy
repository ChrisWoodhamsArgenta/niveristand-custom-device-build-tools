package ni.vsbuild.steps

class LvBuildAllWithVersionStep extends LvBuildStep {

   LvBuildAllWithVersionStep(script, mapStep, lvVersion) {
      super(script, mapStep, lvVersion)
   }

   void executeBuildStep(String projectPath) {
      script.lvBuildAllWithVersion(projectPath, lvVersion)
   }
}
