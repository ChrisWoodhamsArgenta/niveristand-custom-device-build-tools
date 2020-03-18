def call(feed, pkg_name){
   def programFiles = getWindowsVar("PROGRAMFILES")

   // http://www.ni.com/documentation/en/ni-package-manager/18.5/manual/build-package-using-cli/
   //   def nipkgExePath = "$programFiles\\National Instruments\\NI Package Manager\\nipkg.exe"
   def publishScript = "PublishPackage.bat"
   def package_path = "$WORKSPACE\\Built\\installer\\cobham-software_1.1.9_windows_x64.nipkg"
   bat "\"$publishScript\" \"$feed\" \"$package_path\""

}
