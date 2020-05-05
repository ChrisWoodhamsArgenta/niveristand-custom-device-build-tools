def call(project, lvVersion){
   echo "Building all build specs in project at $project"
   def logFileName = getLogName(project)
   labviewcli("-OperationName ExecuteAllBuildSpecsWithVersion -ProjectPath \"$WORKSPACE\\$project\" -Version \"1.2.3.4\" -LogFilePath \"$WORKSPACE\\lvBuildAll_${logFileName}.log\"", lvVersion)
}
