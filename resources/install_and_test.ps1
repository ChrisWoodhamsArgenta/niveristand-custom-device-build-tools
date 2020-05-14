param([string] $package_name, [string] $exe_path, [string] $version )

nipkg install $package_name -y --accept-eulas


.\test_app.ps1 -exe_path $exe_path -version $version




