def call(feed, package){
   def programFiles = getWindowsVar("PROGRAMFILES")

   // http://www.ni.com/documentation/en/ni-package-manager/18.5/manual/build-package-using-cli/
   //   def nipkgExePath = "$programFiles\\National Instruments\\NI Package Manager\\nipkg.exe"
   def nipkgExePath = "nipkg.exe"
   package_path = "$WORKSPACE\\Built\\installer\\cobham-software_1.1.8_windows_x64.nipkg"
   bat "\"$nipkgExePath\" feed-add-pkg \"$feed\" \"$package_path\""

}
