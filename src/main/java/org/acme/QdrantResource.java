package org.acme;

import java.util.List;
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
  @Produces(MediaType.APPLICATION_JSON)
  public List<String> ServeCollections() throws Exception {
    return qdrantService.getCollections();
  }
}