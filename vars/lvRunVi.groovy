def call(vi,args, lvVersion){
   echo "Running $vi."
   def logFileName = getLogName(vi)
   labviewcli("-OperationName RunVI -VIPath \"$WORKSPACE\\$vi\" $args -LogFilePath \"$WORKSPACE\\lvRunVi_${logFileName}.log\"", lvVersion)
}
