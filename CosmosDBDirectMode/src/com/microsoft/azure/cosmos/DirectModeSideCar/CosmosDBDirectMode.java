package com.microsoft.CosmosDBDirectMode;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.microsoft.azure.documentdb.ConnectionMode;
import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.ConsistencyLevel;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentClientException;
import org.springframework.boot.SpringApplication;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class CosmosDBDirectMode
{
    public final JCommander jCommander;
    @Parameter
    private List<String> parameters = new ArrayList<>();

    @Parameter(
            names = {"--endpoint", "-e"},
            description = "Cosmos DB Endpoint"
    )
    private String accountUrl = "";

    @Parameter(
            names = {"--key", "-k"},
            description = "Cosmos DB Key"
    )
    private String accountKey = "";

    @Parameter(
            names = {"--help"},
            help = true,
            description = "Usage"
    )
    public boolean help = false;

    @Parameter(
            names = {"--PreferredRegion", "-pr"},
            description = "Preferred Region to connect to CosmosDB"
    )
    private String preferredRegion = "";

    public static DocumentClient client = null;


    public CosmosDBDirectMode(String[] args) {
        jCommander = new JCommander(this, args);
    }

    public void InitializeDocumentClient() throws DocumentClientException {
        ConnectionPolicy policy = ConnectionPolicy.GetDefault();
        policy.setConnectionMode(ConnectionMode.DirectHttps);
        policy.setUserAgentSuffix(" " + ManagementFactory.getRuntimeMXBean().getName());

        // Set preferred location as ex: "East US" / "West US" if provided as parameter
        if (!preferredRegion.isEmpty()) {
            Collection<String> preferredLocations = Arrays.asList(preferredRegion);
            policy.setEnableEndpointDiscovery(true);
            policy.setPreferredLocations(preferredLocations);

            System.out.println("Preferred Region to connect to CosmosDB is: " + preferredRegion);
        }

        policy.setMaxPoolSize(2000);

        client = new DocumentClient(accountUrl, accountKey, policy, ConsistencyLevel.Eventual);

    }

    public static void main(String[] args) throws DocumentClientException
    {
        // Initialize CosmosDBDirectMode
        CosmosDBDirectMode server = new CosmosDBDirectMode(args);

        if (server.help) {
            server.jCommander.usage();
            return;
        }

        server.InitializeDocumentClient();

        Application app = new Application();
        app.setDocumentClient(client);

        // Run the Spring webserver
        SpringApplication.run(Application.class, args);
    }
}
