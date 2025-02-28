package org.sahni.resources;

import io.quarkus.security.Authenticated;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.sahni.models.requests.LogInRequest;
import org.sahni.models.requests.SignUpRequest;
import org.sahni.models.responses.LogInResponse;
import org.sahni.models.responses.UserResponse;
import org.sahni.services.UsersService;

@Path(value = "/users")
public class Users {

    @Inject
    UsersService usersService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public Uni<UserResponse> getUser(
            @QueryParam(value = "id") Long id
    ) {
        return usersService.getUser(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/signup")
    public Uni<Response> signUpUser(
            SignUpRequest signUpRequest
    ) {
        return usersService.signUpUser(signUpRequest);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Uni<LogInResponse> logInUser(
            LogInRequest logInRequest
    ) {
        return usersService.logInUser(logInRequest);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Authenticated
    @Path("/generate-otp")
    public Uni<Void> generateOTP(
            @QueryParam(value = "id") Long id
    ) {
        return usersService.generateOTP(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    @Path("/verify-otp")
    public Uni<UserResponse> verifyOTP(
            @QueryParam(value = "id") Long id
    ) {
        return usersService.getUser(id);
    }

}
