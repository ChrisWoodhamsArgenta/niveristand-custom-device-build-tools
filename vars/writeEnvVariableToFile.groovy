def call(variable, path){
   echo "writing enviroment variable to a file"
   // not finished it will output only BRANCH_NAME
   if(variable == BUILD_NUMBER)
   {
	bat "echo %BUILD_NUMBER%> \"$WORKSPACE\\${path}\""
   }
   else
   {
	bat "echo %BRANCH_NAME%> \"$WORKSPACE\\${path}\""
   }
     
}
