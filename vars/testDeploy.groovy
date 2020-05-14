def call(feed,pkg_name,exe_path,lvVersion){
   echo "Testing deployment of $pkg_name"
   
   
   // version resolving -> should be move into one common function
    def baseVersion = "0.1.0"
    if( env.BRANCH_NAME.startsWith("release") || env.BRANCH_NAME.startsWith("hotfix")){
    baseVersion = env.BRANCH_NAME.split("[-/]")[1]
	}
    def versionPartCount = baseVersion.tokenize(".").size()

    def versionPartsToDisplay = 3
    for(versionPartCount; versionPartCount < versionPartsToDisplay; versionPartCount++) {
        baseVersion = "${baseVersion}.0"
    }
   
	def build_num = env.BUILD_NUMBER
	
	//def version = $baseVersion + "." + build_num
    echo "$baseVersion"
	echo "$build_num"
   
   def version = "$baseVersion.$build_num"
   echo "$version"
   
   // now build and test packages
   def instalANDtestScript = "$WORKSPACE\\niveristand-custom-device-build-tools\\resources\\install_and_test.bat"
   bat "\"$instalANDtestScript\" $pkg_name $exe_path"
}
