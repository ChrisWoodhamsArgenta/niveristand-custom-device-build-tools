def call(lvVersion,workspace, branchName,triggerType,nodeLabel) {
   def programFiles = getWindowsVar("PROGRAMFILES(x86)")
   def baseVersion = (lvVersion =~ /^[0-9]+/).getAt(0)
   env."labviewPath_${lvVersion}" = "$programFiles\\National Instruments\\LabVIEW ${baseVersion}\\LabVIEW.exe"
   bat "niveristand-custom-device-build-tools\\resources\\buildSetup.bat ${workspace} ${branchName} ${triggerType}"
}
