def call(feed, pkg_name){
   //bat "mkdir $WORKSPACE\\gpm_tmp"
   //bat "mkdir $WORKSPACE\\gpm_tmp\\source"
   bat "xcopy /E /I $WORKSPACE\\source $WORKSPACE\\gpm_tmp\\source"
   bat "xcopy /E /I $WORKSPACE\\gpackage.json $WORKSPACE\\gpm_tmp\\gpackage.json"
  
   bat "$WORKSPACE\\niveristand-custom-device-build-tools\\resources\\updated_gpm_build_number.bat $WORKSPACE"
      bat "cd $WORKSPACE\\gpm_tmp"
   bat "gpm publish"
}
