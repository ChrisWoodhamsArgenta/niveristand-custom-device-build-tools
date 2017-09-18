def call(project, lvVersion){
   echo "Building all build specs in project at $project"
   bat "labview-cli --kill --lv-ver $lvVersion \"$WORKSPACE\\commonbuild\\lv\\lvBuildAll.vi\" -- \"$project\" \"$WORKSPACE\""
}
