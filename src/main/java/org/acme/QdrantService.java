package org.acme;

import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;

import java.util.List;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QdrantService {

    @ConfigProperty(name = "qdrant.host")
    String host;

    @ConfigProperty(name = "qdrant.port")
    int port;

    @ConfigProperty(name = "qdrant.api-key")
    String apiKey;

    public List<String> printCollections() throws Exception {
        try (QdrantClient client = new QdrantClient(
                QdrantGrpcClient.newBuilder(host, port, true)
                        .withApiKey(apiKey)
                        .build())) {
            List<String> collections = client.listCollectionsAsync().get();
            return collections;
        }
    }
}