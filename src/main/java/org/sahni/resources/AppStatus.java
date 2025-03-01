package org.sahni.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/app_status")
public class AppStatus {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String appStatus() {
        return "All Okay.";
    }
}
