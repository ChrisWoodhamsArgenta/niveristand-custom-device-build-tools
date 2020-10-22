def call(feed, pkg_name,pkg_version,dependencies,package_dst_path,package_src_path){

	def package_abs_src_path = "$WORKSPACE\\$package_src_path"
	def pkg_dir = "$WORKSPACE\\$nipkg"
  
	def buildandpublishScript = "$WORKSPACE\\niveristand-custom-device-build-tools\\resources\\NIPKGBuildAndPublish.bat"
   
	bat "\"$buildandpublishScript\" \"$feed\" \"$pkg_name\" \"$pkg_version\" \"$dependencies\" \"$package_dst_path\"  \"$package_abs_src_path\" \"$pkg_dir\" "   
}
