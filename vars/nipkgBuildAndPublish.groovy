def call(feed, pkg_name,pkg_version,dependencies,package_dst_path,package_src_path){
   def programFiles = getWindowsVar("PROGRAMFILES")
   def package_folder_path = "$WORKSPACE\\$pkg_name"

	//def pkg_version = "0.0.0-1"
	//def dependencies = ""
	//def package_dst_path = "BootVolume\\Argenta\\TestApp"
	def package_abs_src_path = "$WORKSPACE\\$Built"
	
	def pkg_dir = "$WORKSPACE\\$nipkg"
  
   def buildandpublishScript = "$WORKSPACE\\niveristand-custom-device-build-tools\\resources\\NIPKGBuildAndPublish.bat"
   
   bat "\"$buildandpublishScript\" \"$feed\" \"$pkg_name\" \"$pkg_version\" \"$dependencies\" \"$package_dst_path\"  \"$package_abs_src_path\" \"$pkg_dir\" " 
   
}
