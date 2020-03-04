def call(args, lvVersion){
   def versionPath = "C:\\Program Files\\National Instruments\\LabVIEW 2019\\LabVIEW.exe"
   bat "LabVIEWCLI -LabVIEWPath \"${versionPath}\" -AdditionalOperationDirectory \"$WORKSPACE\\niveristand-custom-device-build-tools\\lv\\operations\" $args"
   lvCloseLabVIEW()
}
