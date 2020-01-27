def call(bat_cmd, lvVersion){
   echo "runing batch command $bat_cmd."
   bat returnStdout: true, script: "$bat_cmd"
}
