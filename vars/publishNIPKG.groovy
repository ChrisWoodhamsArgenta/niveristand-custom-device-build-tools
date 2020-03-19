def call(feed, pkg_name){
   def programFiles = getWindowsVar("PROGRAMFILES")

   // http://www.ni.com/documentation/en/ni-package-manager/18.5/manual/build-package-using-cli/
   //   def nipkgExePath = "$programFiles\\National Instruments\\NI Package Manager\\nipkg.exe"
   bat "GetPackageName.bat $pkg_name"
   def packagePath = bat returnStdout: true, script: "GetPackageName.bat $pkg_name"
   def publishScript = "PublishPackage.bat"
   def package_path = "$WORKSPACE\\Built\\installer\\$packagePath"
   bat "\"$publishScript\" \"$feed\" \"$package_path\""

}
