param([string] $pkg_name = "test-pkg",[string] $pkg_version = "0.0.0-1", [string] $pkg_dir = "C:\Projects\niveristand-custom-device-build-tools\resources\nipkg", [string] $package_src_path = "C:\Projects\test", [string] $package_dst_path = "BootVolume\Argenta\TestApplication",[string] $dependencies = "")

#Remove top level directory recursively if exists
Remove-Item $pkg_dir -Recurse -Force -ErrorAction SilentlyContinue

#create base directory structure
$control_dir = $pkg_dir+"\\control"
$data_dir = $pkg_dir+"\\data"

New-Item -ItemType Directory -Path $control_dir
New-Item -ItemType Directory -Path $data_dir

#create required by NIPKG debian file
$debianbinaryFilePath = $pkg_dir+"\\debian-binary"
Set-Content -Path $debianbinaryFilePath -Value "2.0"


#assemble control file
$controlFile = 
"Architecture: windows_x64
Homepage: http://www.argentaconsult.com/
Maintainer: Argenta <>
Description: package descritpion
XB-Plugin: file
XB-UserVisible: yes
XB-StoreProduct: yes
XB-Section: Application Software
"

$nameLine = "Package: "+$pkg_name + "`n"
$versionLine = "Version: "+$pkg_version + "`n"
$dependencyLine = "Depends: "+$dependencies + "`n"

$controlFile = $controlFile + $nameLine + $versionLine + $dependencyLine
$controlFile


#save control file
$controlFilePath = $control_dir+"\\control"
Set-Content -Path $controlFilePath -Value $controlFile


# copy content of source directory to destination directory
$absolute_dst_path = $data_dir + "\" + $package_dst_path
Copy-Item -Path $package_src_path -Destination $absolute_dst_path -Recurse

nipkg pack $pkg_dir $pkg_dir

# find and return package name
$files = Get-ChildItem -Path $pkg_dir -Filter *.nipkg -Recurse
return $files[0].Name

<#
$files = Get-ChildItem -Path $package_src_path
$package_name = $files.Name
$package_dst_path = $feed+"\" + $package_name
Copy-Item -Path $package_src_path -Destination $feed 
nipkg feed-add-pkg $feed $package_dst_path
#>