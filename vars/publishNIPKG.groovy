def call(feed, pkg_name){
   def programFiles = getWindowsVar("PROGRAMFILES")

   // http://www.ni.com/documentation/en/ni-package-manager/18.5/manual/build-package-using-cli/
   //   def nipkgExePath = "$programFiles\\National Instruments\\NI Package Manager\\nipkg.exe"
   def package_folder_path = "$WORKSPACE\\$pkg_name"
   bat "GetPackageName.bat $package_folder_path"
   def packageName = bat "GetPackageName.bat $package_folder_path"
   //def packageName = bat returnStdout: true, script: "GetPackageName.bat $package_folder_path"
   def publishScript = "PublishPackage.bat"
   def package_path = "$WORKSPACE\\Built\\installer\\$packageName"
   bat "\"$publishScript\" \"$feed\" \"$package_path\""

}
