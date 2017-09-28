package ni.vsbuild.nipm.groovy

import ni.vsbuild.AbstractPipelineBuilder
import ni.vsbuild.BuildInformation

class PipelineBuilder extends AbstractPipelineBuilder {
   
   PipelineBuilder(script, BuildInformation buildInformation) {
      super(script, buildInformation)
   }
   
   void build {
      withInitialCleanStage()
      withCheckoutStage()
      
      withSetupStage()
      
      withCodegenStage()
      withBuildStage()
      
      withArchiveStage()
      withPackageStage()
      
      withPublishStage()
      
      withCleanupStage()
   }
}
