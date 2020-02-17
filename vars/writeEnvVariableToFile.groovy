def call(variable, path){
   echo "writing enviroment variable to a file"
   // not finished it will output only GIT_BRANCH
     bat "echo \"$GIT_BRANCH\" > \"$WORKSPACE\\${path}\""
}
