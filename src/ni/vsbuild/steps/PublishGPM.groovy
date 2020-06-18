package ni.vsbuild.steps

import ni.vsbuild.BuildConfiguration

class PublishGPM extends LvStep {

   def registry
   def pkg_name

   PublishGPM(script, mapStep, lvVersion) {
      super(script, mapStep, lvVersion)
      this.registry = mapStep.get('registry')
	  this.pkg_name = mapStep.get('package')
   }

   void executeStep(BuildConfiguration configuration) {
	  $ws = "${script.env.WORKSPACE}"
	  $src_source = "$ws\\source\\"
	  $dst_source = "$ws\\gpm_tmp\\source\\"
	  script.copyFiles(src_source, dst_source)
      script.publishGPM(registry, pkg_name)
   }
}
