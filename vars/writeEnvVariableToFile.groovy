def call(variable, path){
   echo "writing enviroment variable to a file"
   // not finished it will output only GIT_BRANCH
<<<<<<< HEAD
	echo script.env.JOB_NAME
	echo script.env.GIT_BRANCH
	def myVar = script.env.GIT_BRANCH
=======
	echo env.JOB_NAME
	echo env.GIT_BRANCH
	def myVar = env.GIT_BRANCH
>>>>>>> fa28c93e9e35e4521a0c2abe8e3ccc0f2fafd337
	echo myVar
     bat "echo $myVar > \"$WORKSPACE\\${path}\""
}
