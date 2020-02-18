def call(bat_cmd, lvVersion){
   echo "runing batch command $bat_cmd."
   bat script: "$bat_cmd"
}
