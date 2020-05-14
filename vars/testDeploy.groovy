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
   
   // add standard feeds
   
   //bat "nipkg add-feed --name=ni-labview-2017-runtime-engine-x86-2017-released https://download.ni.com/support/nipkg/products/ni-l/ni-labview-2017-runtime-engine-x86/17.6/released"
   //bat "nipkg add-feed --name=ni-labview-2017-runtime-engine-x86-2017-released-critical https://download.ni.com/support/nipkg/products/ni-l/ni-labview-2017-runtime-engine-x86/17.6/released-critical"
   //bat "nipkg add-feed --name=ni-xnet-19-5-released https://download.ni.com/support/nipkg/products/ni-x/ni-xnet/19.5/released"
	def addFeedsScript = "$WORKSPACE\\niveristand-custom-device-build-tools\\resources\\add_feeds.bat"
	bat "addFeedsScript"
   
   // now build and test packages
   //def instalANDtestScript = "$WORKSPACE\\niveristand-custom-device-build-tools\\resources\\install_and_test.bat"
   def installScript = "$WORKSPACE\\niveristand-custom-device-build-tools\\resources\\install_package.bat"
   def testScript = "$WORKSPACE\\niveristand-custom-device-build-tools\\resources\\test_app.bat"
   bat "nipkg add-feed --name=test_feed $feed"
   bat "nipkg update"
   bat "\"$installScript\" $pkg_name"
   bat "\"$testScript\" \"$exe_path\" $version"
}
