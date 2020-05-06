def call(lvVersion,workspace, branchName,triggerType,nodeLabel) {
	echo "Node $nodeLabel"
   def programFiles = getWindowsVar("PROGRAMFILES(x86)")
   if(nodeLabel.contains("x64")){
		programFiles = getWindowsVar("PROGRAMFILES")
   }
   def baseVersion = (lvVersion =~ /^[0-9]+/).getAt(0)
   
   echo "LV path: $programFiles\\National Instruments\\LabVIEW ${baseVersion}\\LabVIEW.exe"
   env."labviewPath_${lvVersion}" = "$programFiles\\National Instruments\\LabVIEW ${baseVersion}\\LabVIEW.exe"
   bat "niveristand-custom-device-build-tools\\resources\\buildSetup.bat ${workspace} ${branchName} ${triggerType}"
}
