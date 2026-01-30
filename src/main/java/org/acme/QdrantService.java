package org.acme;

import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import io.qdrant.client.grpc.Collections;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QdrantService {

    @ConfigProperty(name = "qdrant.host")
    String host;

    @ConfigProperty(name = "qdrant.port")
    int port;

    @ConfigProperty(name = "qdrant.api-key")
    String apiKey;

    private QdrantClient client;

    @PostConstruct
    void init() {
        this.client = new QdrantClient(
                QdrantGrpcClient.newBuilder(host, port, true)
                        .withApiKey(apiKey)
                        .build());
    }

    public List<String> getCollections() throws ExecutionException, InterruptedException {
        return client.listCollectionsAsync().get();
    }

    public Collections.CollectionInfo getCollectionDetails(String collectionName)
            throws ExecutionException, InterruptedException {
        return client.getCollectionInfoAsync(collectionName).get();
    }

    @PreDestroy
    void cleanup() {
        if (client != null) {
            client.close();
        }
    }
}