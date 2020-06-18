%SYSTEMROOT%\System32\WindowsPowerShell\v1.0\powershell.exe -executionpolicy remotesigned -File %~dp0\updated_gpm_build_number.ps1 -workspace %1 -buildNumber %BUILD_NUMBER%
IF %ERRORLEVEL% NEQ 0 ( 
	EXIT %ERRORLEVEL%
)