package com.microsoft.azure.cosmos.DirectModeSideCar;

import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.documentdb.DocumentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DocumentClientController {
    @Autowired
    private DocumentClient client;

    @RequestMapping(value = "/{DatabaseName}/{CollectionName}/getdocument", method = RequestMethod.GET)
    public String getdocument(
            @PathVariable String DatabaseName,
            @PathVariable String CollectionName,
            @RequestParam(value="id") String id) {
        if (client == null) {
            return null;
        }
        else {
            String collLink = "/dbs/" + DatabaseName + "/colls/" + CollectionName;

            List<Document> documentList = client.queryDocuments(
                    collLink,
                    "SELECT * FROM root r WHERE r.id='" + id + "'", null)
                    .getQueryIterable()
                    .toList();

            if (documentList.size() > 0) {
                return documentList.get(0).toJson();
            }
            else {
                return "Error: Document not found";
            }
        }
    }
}
