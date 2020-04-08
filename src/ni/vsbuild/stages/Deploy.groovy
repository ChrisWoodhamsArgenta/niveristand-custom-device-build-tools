package ni.vsbuild.stages

class Publish extends AbstractStepStage {

   Deploy(script, configuration, lvVersion) {
      super(script, 'Deploy', configuration, lvVersion)
   }

   void executeStage() {
      executeSteps(configuration.deploy)
   }
}
