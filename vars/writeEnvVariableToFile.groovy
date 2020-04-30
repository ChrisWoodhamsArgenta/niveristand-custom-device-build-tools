def call(variable, path){
   echo "writing enviroment variable to a file"
   // not finished it will output only BRANCH_NAME
   myVar = BRANCH_NAME
   if(variable == BUILD_NUMBER)
   {
	myVar =BUILD_NUMBER
   }
	echo myVar
     bat "echo %$myVar%> \"$WORKSPACE\\${path}\""
}
