def call(variable, path){
   echo "writing enviroment variable to a file"
   // not finished it will output only GIT_BRANCH
	echo env.JOB_NAME
	echo env.GIT_BRANCH
	def myVar = env.GIT_BRANCH
	echo myVar
     bat "echo $myVar > \"$WORKSPACE\\${path}\""
}
