Powershell.exe -executionpolicy remotesigned -File C:\Autobuild\PublishPackage.ps1 -feed %1 -package_src_path %2
IF %ERRORLEVEL% NEQ 0 ( 
	EXIT %ERRORLEVEL%
)

