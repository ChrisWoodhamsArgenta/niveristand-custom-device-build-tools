def call(variable, variable_value, path){
   echo "writing enviroment variable to a file"
   // not finished it will output only BRANCH_NAME
	def myVar = variable_value
	echo myVar
	myVar = env.BRANCH_NAME
	echo myVar
     bat "echo %BRANCH_NAME%> \"$WORKSPACE\\${path}\""
}
