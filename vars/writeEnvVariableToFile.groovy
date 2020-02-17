def call(variable, path){
   echo "writing enviroment variable to a file"
     bat "echo \"$WORKSPACE\\${variable}\" > \"$WORKSPACE\\${path}\""
}
