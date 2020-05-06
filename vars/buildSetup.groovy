def call(lvVersion,workspace, branchName,triggerType,nodeLabel) {
	echo "Node $nodeLabel ${nodeLabel}."
   def programFiles = getWindowsVar("PROGRAMFILES(x86)")
   if(nodeLabel.grep("x64") || nodeLabel.contains("x64")){
		programFiles = getWindowsVar("PROGRAMFILES")
   }
   def baseVersion = (lvVersion =~ /^[0-9]+/).getAt(0)
   env."labviewPath_${lvVersion}" = "$programFiles\\National Instruments\\LabVIEW ${baseVersion}\\LabVIEW.exe"
   bat "niveristand-custom-device-build-tools\\resources\\buildSetup.bat ${workspace} ${branchName} ${triggerType}"
}
