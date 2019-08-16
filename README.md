# CosmosDirectMode

Provides a side car implementation for DirectMode. This could be used as a local gateway to achieve high perf.


## Build Instructions

> set MAVEN_OPTS=-Xss10M

> mvn clean package

> java -jar CosmosDBDirectMode-1.0-SNAPSHOT.jar com.microsoft.CosmosDBDirectMode.CosmosDBDirectMode -e <Cosmos Account Endpoint Url> -k <Account Key>

example:
> java -jar CosmosDBDirectMode-1.0-SNAPSHOT.jar com.microsoft.CosmosDBDirectMode.CosmosDBDirectMode -e https://myAccount.documents.azure.com:443/ -k abcedtAUJq0SyX6VeCCTuB26eyeMAVxnUN4xbjsnWFehcVuEXndX4GkAzOt4enaqUq0rlrA4UTLVvgGvBp0Gab==
