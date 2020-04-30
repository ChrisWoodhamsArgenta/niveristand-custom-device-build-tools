package ni.vsbuild.stages

class Deploy extends AbstractStepStage {

   Deploy(script, configuration, lvVersion) {
      super(script, 'TestDeploy', configuration, lvVersion)
   }

   void executeStage() {
      executeSteps(configuration.testdeploy)
   }
}
