package org.digitalsprouts.example.http_client.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class Root {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getText() {
        // echo hello world in Javanese
        return Response.ok("Hello donya!").build();
    }

}