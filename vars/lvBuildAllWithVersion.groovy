def call(project, lvVersion){
   echo "Building all build specs in project at $project"
   
    def baseVersion = env.BRANCH_NAME.split("[-/]")[1]
    def versionPartCount = baseVersion.tokenize(".").size()

    def versionPartsToDisplay = 3
    for(versionPartCount; versionPartCount < versionPartsToDisplay; versionPartCount++) {
        baseVersion = "${baseVersion}.0"
    }
   
	def build_num = env.BUILD_NUMBER
	
	def version = $baseVersion + "." + build_num
    echo "$version"
   
   def logFileName = getLogName(project)
   labviewcli("-OperationName ExecuteAllBuildSpecsWithVersion -ProjectPath \"$WORKSPACE\\$project\" -Version \"$version\" -LogFilePath \"$WORKSPACE\\lvBuildAll_${logFileName}.log\"", lvVersion)
}
