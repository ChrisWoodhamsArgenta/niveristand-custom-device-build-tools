def call(dir_to_mc, lvVersion){
   echo "Mass Compiling $dir_to_mc."
   def logFileName = "mc_log"
   labviewcli("-OperationName MassCompile -DirectoryToCompile \"$WORKSPACE\\$dir_to_mc\" -MassCompileLogFile \"$WORKSPACE\\lvRunVi_${logFileName}.log\"", lvVersion)
}
