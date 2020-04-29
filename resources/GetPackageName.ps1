param([string] $folder_path)

$files = Get-ChildItem -Path $folder_path -Filter *.nipkg -Recurse
return $files[0].Name