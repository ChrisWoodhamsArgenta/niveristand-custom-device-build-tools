echo %1
Powershell.exe -executionpolicy remotesigned -File %~dp0\test_app.ps1 -exe_path %1 -version %2
