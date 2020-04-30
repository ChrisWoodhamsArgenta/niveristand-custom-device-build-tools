param([string] $workspace)

try
{
    $path = $workspace + "\buildnumber.txt"
    $buildNumber = Get-Content $path
    $buildNumber = $buildNumber.Trim();

    <#
    $jsonContent = Get-Content .\gpackage.json 
    echo $jsonContent
    $regex = [char]34 +"version"+[char]34+ ":.+?"+[char]34+"[0-9]+?.[0-9]+?.[0-9]+?-[0-9]+?"
    $jsonContent1 = $jsonContent -replace $regex ,"dupa"
    echo $jsonContent1
    #>

    <#
    $jsonContent = Get-Content .\gpackage.json 
    echo $jsonContent
    $regex =  "-[0-9]+?"+[char]34
    $buildNmberString = "-"+$buildNumber+[char]34;
    $jsonContent1 = $jsonContent -replace $regex,$buildNmberString;
    $jsonContent1 | Out-File ".\gpackage.json" -Encoding ascii
    #>



    $jsonContent = Get-Content .\gpackage.json | ConvertFrom-Json;
    $result =  $jsonContent.version -replace "-(.?)","";
    $result = $result+"-";
    $result = $result+$buildNumber;
    $jsonContent.version = $result;
    $jsonContent | ConvertTo-Json -depth 100 | Out-File ".\gpackage.json" -Encoding ascii


    # https://stackoverflow.com/questions/32959431/json-file-to-powershell-and-back-to-json-file
    $dReplacements = @{
        "\\u003c" = "<"
        "\\u003e" = ">"
        "\\u0027" = "'"
    }

    $sRawJson = Get-Content -Path .\gpackage.json | Out-String
    foreach ($oEnumerator in $dReplacements.GetEnumerator()) {
        $sRawJson = $sRawJson -replace $oEnumerator.Key, $oEnumerator.Value
    }
    $sRawJson | Out-File -FilePath .\gpackage.json -Encoding ascii
}
catch
{
throw
}