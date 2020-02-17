def call(variable, variable_value, path){
   echo "writing enviroment variable to a file"
   // not finished it will output only GIT_BRANCH
	def myVar = variable_value
	echo myVar
	myVar = env.GIT_BRANCH
	echo myVar
     bat "echo %GIT_BRANCH%> \"$WORKSPACE\\${path}\""
}
