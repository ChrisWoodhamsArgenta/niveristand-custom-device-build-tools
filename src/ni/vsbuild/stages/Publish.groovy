package ni.vsbuild.stages

class Publish extends AbstractStepStage {

   Publish(script, configuration, lvVersion) {
      super(script, 'Publish', configuration, lvVersion)
   }

   void executeStage() {
      executeSteps(configuration.publish)
   }
}
