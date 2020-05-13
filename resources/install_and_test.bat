echo %2
Powershell.exe -executionpolicy remotesigned -File %~dp0\install_and_test.ps1 -package_name %1 -exe_path %2
