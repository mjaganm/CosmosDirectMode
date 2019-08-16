# CosmosDirectMode

Provides a side car implementation for Cosmos DB DirectMode for applications running in C++/Node.js or others where Cosmos SDK does not support Direct Mode. This could be used as a local gateway to achieve high perf reads.



## Maven Instructions

### Build

    set MAVEN_OPTS=-Xss10M

    mvn clean package

### Execution

    java -jar DirectModeSideCar-1.0-SNAPSHOT.jar com.microsoft.azure.cosmos.DirectModeSideCar.CosmosDBDirectMode -e Cosmos-Account-Endpoint-Url -k Account-Key

example:

    java -jar DirectModeSideCar-1.0-SNAPSHOT.jar com.microsoft.azure.cosmos.DirectModeSideCar.CosmosDBDirectMode -e https://myAccount.documents.azure.com:443/ -k abcedtAUJq0SyX6VeCCTuB26eyeMAVxnUN4xbjsnWFehcVuEXndX4GkAzOt4enaqUq0rlrA4UTLVvgGvBp0Gab==



## Intellij Instructions

### Build and Execute

1. Tab "Build" => "Rebuild Project"

2. Tab "Run" => "Edit Configurations" => Click on "+" => Select "Application" and Add Name: DirectModeSidecar

3. Add Main Class: com.microsoft.azure.cosmos.DirectModeSideCar.CosmosDBDirectMode

4. Add Program Arguments: com.microsoft.azure.cosmos.DirectModeSideCar.CosmosDBDirectMode -e https://myAccount.documents.azure.com:443/ -k abcedtAUJq0SyX6VeCCTuB26eyeMAVxnUN4xbjsnWFehcVuEXndX4GkAzOt4enaqUq0rlrA4UTLVvgGvBp0Gab==

5. Click Apply

6. Tab "Run" => "Run DirectModeSidecar"



## Usage Instructions

Test the service is up and running:

    http://localhost:8080/isalive
 
Retrieve a document using DirectMode:

    http://localhost:8080/{DatabaseName}/{CollectionName}/getdocument?id={id-value}
 
    ex: http://localhost:8080/device/activitylog/getdocument?id=59651931-cd31-40a9-a912-d9012518cb92



## Troubleshooting

1. This application supports only "http" and not "https". Chrome seems to send an "http" request modified to "https" causing failures "The site can't be reached"

Ex: http://localhost:8080/isalive  => https://localhost:8080/isalive and failure

Mitigation: Use "Edge" or other browser that does not have this default behavior for testing. Applications that are using http protocol are able to successfully connect.
