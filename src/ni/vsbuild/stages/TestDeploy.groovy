package ni.vsbuild.stages

class TestDeploy extends AbstractStepStage {

   TestDeploy(script, configuration, lvVersion) {
      super(script, 'TestDeploy', configuration, lvVersion)
   }

   void executeStage() {
      executeSteps(configuration.testdeploy)
   }
}
