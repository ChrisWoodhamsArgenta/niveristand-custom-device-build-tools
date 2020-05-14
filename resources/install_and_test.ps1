param([string] $package_name, [string] $exe_path, [string] $version )

nipkg install $package_name -y --accept-eulas

$PSScriptRoot

$ScriptToRun = $PSScriptRoot+"\test_app.ps1 -exe_path " + $exe_path +" -version "+ $version

&$ScriptToRun






