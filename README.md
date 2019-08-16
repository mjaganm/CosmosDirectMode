# CosmosDirectMode

Provides a side car implementation for DirectMode. This could be used as a local gateway to achieve high perf.


## Build Instructions

> set MAVEN_OPTS=-Xss10M

> mvn clean package

> java -jar CosmosDBDirectMode-1.0-SNAPSHOT.jar com.microsoft.CosmosDBDirectMode.CosmosDBDirectMode -e Cosmos-Account-Endpoint-Url -k Account-Key

example:
> java -jar CosmosDBDirectMode-1.0-SNAPSHOT.jar com.microsoft.CosmosDBDirectMode.CosmosDBDirectMode -e https://myAccount.documents.azure.com:443/ -k abcedtAUJq0SyX6VeCCTuB26eyeMAVxnUN4xbjsnWFehcVuEXndX4GkAzOt4enaqUq0rlrA4UTLVvgGvBp0Gab==


## Usage Instructions

Test the service is up and running: http://localhost:8080/isalive
Retrieve a document using DirectMode: http://localhost:8080/{DatabaseName}/{CollectionName}/getdocument?id={id-value}
ex: http://localhost:8080/device/activitylog/getdocument?id=59651931-cd31-40a9-a912-d9012518cb92


## Troubleshooting

1. This application supports only "http" and not "https". Chrome seems to send an "http" request modified to "https" causing failures "The site can't be reached"

Ex: http://localhost:8080/isalive  => https://localhost:8080/isalive and failure

Mitigation: Use "Edge" or other browser that does not have this default behavior for testing


