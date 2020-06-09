param([string] $exe_path, [string] $version)

$string = '&"{0}" version' -f $exe_path
$string


$scriptBlock = [Scriptblock]::Create($string)
#$code = { "C:\Argenta\Tenneco_EGDRigs500V\CAN File Converter\CAN File Converter.bat version"}
$code = $scriptBlock

$timeoutSeconds = 20 # set your timeout value here
$j = Start-Job -ScriptBlock $code
Wait-Job $j -Timeout $timeoutSeconds | out-null
if ($j.State -eq "Completed") 
{
    $result = Receive-Job -Job $j
    $app_ver =  $result -match '[0-9]\.[0-9]\.[0-9]\.[0-9]'
    $app_ver = $app_ver -replace "v", ""

    if($app_ver -eq $version){ 
         $msg = "Success! "+"Expected version: "+$version + ". Installed version: " + $app_ver +"."
         $msg
    }
    else { 
        $err_msg = "version not equal to: " +$version
        throw $err_msg
    }
   
    

}
elseif ($j.State -eq "Running") { throw "not openned on time" }
else 
{ 
   throw "up not started within 10 sec"
}

Remove-Job -force $j #cleanup
