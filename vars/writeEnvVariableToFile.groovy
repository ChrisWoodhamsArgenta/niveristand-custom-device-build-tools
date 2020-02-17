def call(variable, variable_value, path){
   echo "writing enviroment variable to a file"
   // not finished it will output only GIT_BRANCH
	def myVar = variable_value
	echo myVar = env.GIT_BRANCH
     bat "echo $myVar > \"$WORKSPACE\\${path}\""
}
