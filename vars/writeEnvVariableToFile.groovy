def call(variable, path){
   echo "writing enviroment variable to a file"
   // not finished it will output only BRANCH_NAME
   myVar = env.BRANCH_NAME
   if(variable == BUILD_NUMBER)
   {
	myVar =env.BUILD_NUMBER
   }
	echo myVar
     bat "echo %BRANCH_NAME%> \"$WORKSPACE\\${path}\""
}
