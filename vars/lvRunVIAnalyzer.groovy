def call(cfg_path, lvVersion){
   echo "Running VI Analyzer $cfg_path."
   labviewcli("-OperationName RunVIAnalyzer -ConfigPath \"$WORKSPACE\\$cfg_path\" -ReportPath \"$WORKSPACE\\vi_analyzer_log.log\"", lvVersion)
}
