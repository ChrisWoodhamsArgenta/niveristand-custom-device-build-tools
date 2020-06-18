def call(feed, pkg_name){
   bat "mkdir $WORKSPACE\\gpm_tmp"
   bat "mkdir $WORKSPACE\\gpm_tmp\\source"
   bat "robocopy $WORKSPACE\\source\\ $WORKSPACE\\gpm_tmp\\source\\"
   bat "$WORKSPACE\\niveristand-custom-device-build-tools\\resources\\updated_gpm_build_number.bat $WORKSPACE"
   bat "gpm publish"
}
