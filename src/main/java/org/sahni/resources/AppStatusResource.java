package org.sahni.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/app_status")
public class AppStatusResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String appStatus() {
        return "All Okay.";
    }
}
