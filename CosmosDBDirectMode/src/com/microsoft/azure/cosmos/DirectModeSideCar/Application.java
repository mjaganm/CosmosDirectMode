package com.microsoft.CosmosDBDirectMode;

import com.microsoft.azure.documentdb.DocumentClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application
{
    public static DocumentClient client = null;

    @Bean
    public DocumentClient getDocumentClient()
    {
        return client;
    }

    public void setDocumentClient(DocumentClient cl)
    {
        client = cl;
    }

    public static String collLink = null;

    @Bean
    public String getCollLink()
    {
        return collLink;
    }

    public void setCollLink(String link)
    {
        collLink = link;
    }
}
