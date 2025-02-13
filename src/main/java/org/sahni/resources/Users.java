package org.sahni.resources;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.sahni.models.requests.CreateUserRequest;
import org.sahni.services.UsersService;

@Path(value = "/users")
public class Users {

    @Inject
    UsersService usersService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<org.sahni.models.db.Users> getUser(
            @QueryParam(value = "id") Long id
    ) {
        return usersService.getUser(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<org.sahni.models.db.Users> createUser(
            CreateUserRequest createUserRequest
    ) {
        return usersService.createUser(createUserRequest);
    }

}
