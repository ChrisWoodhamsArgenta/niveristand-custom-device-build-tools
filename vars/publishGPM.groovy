def call(feed, pkg_name){

   bat "$WORKSPACE\\niveristand-custom-device-build-tools\\resources\\updated_gpm_build_number.bat $WORKSPACE"
   bat "gpm pubish"
}
