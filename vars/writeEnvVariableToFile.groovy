def call(variable, path){
   echo "writing enviroment variable to a file"
     bat "echo ${variable} > \"$WORKSPACE\\${path}\""
}
