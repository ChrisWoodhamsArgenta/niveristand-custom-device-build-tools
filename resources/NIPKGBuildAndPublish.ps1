param([string] $feed, [string] $pkg_name,[string] $pkg_version,  [string] $package_src_path, [string] $package_dst_path)




$files = Get-ChildItem -Path $package_src_path
$package_name = $files.Name
$package_dst_path = $feed+"\" + $package_name
Copy-Item -Path $package_src_path -Destination $feed 
nipkg feed-add-pkg $feed $package_dst_path