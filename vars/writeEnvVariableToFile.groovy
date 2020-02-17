def call(variable, path){
   echo "writing enviroment variable to a file"
   // not finished it will output only GIT_BRANCH
	echo script.env.JOB_NAME
	echo script.env.GIT_BRANCH
	def myVar = script.env.GIT_BRANCH
	echo myVar
     bat "echo $myVar > \"$WORKSPACE\\${path}\""
}
