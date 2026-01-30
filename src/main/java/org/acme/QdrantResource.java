package org.acme;

import java.util.List;

import io.qdrant.client.grpc.Collections;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/qdrant")
public class QdrantResource {

  @Inject
  QdrantService qdrantService;

  @GET
  @Path("/collections")
  @Produces(MediaType.APPLICATION_JSON)
  public List<String> serveCollections() throws Exception {
    return qdrantService.getCollections();
  }

  @GET
  @Path("/collections/star_charts")
  @Produces(MediaType.APPLICATION_JSON)
  public Collections.CollectionInfo serveCollectionsDetails() throws Exception {
    return qdrantService.getCollectionDetails("star_charts");
  }
}