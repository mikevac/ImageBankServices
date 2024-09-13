[System.Environment]::SetEnvironmentVariable('HTTPS', 'true')
[System.Environment]::SetEnvironmentVariable('SSL_CRT_FILE', '.\localhost.cert.crt')
[System.Environment]::SetEnvironmentVariable('SSL_KEY_FILE', '.\localhost.keyNP.key')

Get-Item Env:HTTPS
Get-Item Env:SSL_CRT_FILE
Get-Item Env:SSL_KEY_FILE