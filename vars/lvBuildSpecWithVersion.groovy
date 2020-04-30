def call(project, target, spec, lvVersion){
   echo "Building the build spec: $spec under target $target in project at $project"
   
   vi_path = "$WORKSPACE\\niveristand-custom-device-build-tools\\lv\\utils\\modify_build_number.vi"
   version_file_path = "$WORKSPACE\\version.txt"
   build_file_path = "$WORKSPACE\\buildnumber.txt"
   args = "$project $target $spec $version_file_path $build_file_path"
   lvRunVi(vi_path,args,lvVersion)
   labviewcli("-OperationName ExecuteBuildSpec -ProjectPath \"$WORKSPACE\\$project\" -TargetName \"$target\" -BuildSpecName \"$spec\" -LogFilePath \"$WORKSPACE\\lvBuildSpec_${spec}.log\"", lvVersion)
}
