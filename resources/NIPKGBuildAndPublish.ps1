param([string] $feed = "V:\NIPKG\TestFeed" , [string] $pkg_name = "test-pkg",[string] $pkg_version = "0.0.0-3", [string] $pkg_dir = "C:\Projects\niveristand-custom-device-build-tools\resources\nipkg", [string] $package_src_path = "C:\Projects\test", [string] $package_dst_path = "BootVolume\Argenta\TestApplication",[string] $dependencies = "")

$build_script_path = $PSScriptRoot+"\" +"NIPKGBuild.ps1"
$package_full_name = &$build_script_path -pkg_name $pkg_name -pkg_version $pkg_version -pkg_dir $pkg_dir -package_src_path $package_src_path -package_dst_path $package_dst_path -dependencies $dependencies
$package_full_name

$package_path = $pkg_dir + "\" + $package_full_name
$package_path
$package_dst_path = $feed+"\" + $package_full_name
$package_dst_path
Copy-Item -Path $package_path -Destination $package_dst_path 
nipkg feed-add-pkg $feed $package_dst_path