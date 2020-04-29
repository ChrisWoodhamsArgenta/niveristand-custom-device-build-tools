def call(feed, pkg_name){
   def programFiles = getWindowsVar("PROGRAMFILES")
   def package_folder_path = "$WORKSPACE\\$pkg_name"
   // http://www.ni.com/documentation/en/ni-package-manager/18.5/manual/build-package-using-cli/
   //   def nipkgExePath = "$programFiles\\National Instruments\\NI Package Manager\\nipkg.exe"
   
   def nipkgOutput = bat returnStdout: true, script: "echo off & dir \"$package_folder_path\\*.nipkg\" /B & echo on"
   nipkgOutput = (nipkgOutput =~ /[\w\-\+\.]+.nipkg/)[0]
   def packageName = nipkgOutput.trim()
   

   bat "$WORKSPACE\\niveristand-custom-device-build-tools\\resources\\GetPackageName.bat $package_folder_path"
   //def packageName = bat returnStdout: true, script: "GetPackageName.bat $package_folder_path"
   def publishScript = "$WORKSPACE\\niveristand-custom-device-build-tools\\resources\\PublishPackage.bat"
   def package_path = "$package_folder_path\\$packageName"
   bat "\"$publishScript\" \"$feed\" \"$package_path\""

}
