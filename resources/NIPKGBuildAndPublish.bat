Powershell.exe -executionpolicy remotesigned -File %~dp0\NIPKGBuildAndPublish.ps1 -feed %1 -pkg_name %2 -pkg_version %3 -dependencies %4 -package_dst_path %5 -package_src_path %6 -pkg_dir %7
IF %ERRORLEVEL% NEQ 0 ( 
	EXIT %ERRORLEVEL%
)

