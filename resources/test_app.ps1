param([string] $exe_path )

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
    $result
    #if($result -eq "1.1.1.1"){ "Success" }
    #else { throw "version not equalto 1.1.1.2" }
    $msg = "Success! Version: " + $result + " installed."
    $msg

}
elseif ($j.State -eq "Running") { "interrupted" }
else 
{ 
   throw "up not started within 10 sec"
}

Remove-Job -force $j #cleanup
