def call(feed, pkg_name){
   //bat "mkdir $WORKSPACE\\gpm_tmp"
   //bat "mkdir $WORKSPACE\\gpm_tmp\\source"
   bat "xcopy /E /I $WORKSPACE\\source $WORKSPACE\\gpm_tmp\\source"
   
   bat "$WORKSPACE\\niveristand-custom-device-build-tools\\resources\\updated_gpm_build_number.bat $WORKSPACE"
   
   //https://stackoverflow.com/questions/3018289/xcopy-file-rename-suppress-does-xxx-specify-a-file-name-message
   bat "echo f | xcopy /f /y $WORKSPACE\\gpackage.json $WORKSPACE\\gpm_tmp\\gpackage.json"
  

   bat "cd $WORKSPACE\\gpm_tmp & gpm publish"
}
