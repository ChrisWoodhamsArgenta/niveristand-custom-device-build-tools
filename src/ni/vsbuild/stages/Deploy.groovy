package ni.vsbuild.stages

class Deploy extends AbstractStepStage {

   Deploy(script, configuration, lvVersion) {
      super(script, 'Deploy', configuration, lvVersion)
   }

   void executeStage() {
      executeSteps(configuration.deploy)
   }
}
