def call(variable, path){
   echo "writing enviroment variable to a file"
   // not finished it will output only GIT_BRANCH
	println env.JOB_NAME
	println env.GIT_BRANCH
	def myVar = env.GIT_BRANCH
	println myVar
     bat "echo $myVar > \"$WORKSPACE\\${path}\""
}
