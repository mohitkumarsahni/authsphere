package org.sahni.services;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.core.Response;
import org.sahni.models.requests.LogInRequest;
import org.sahni.models.requests.SignUpRequest;
import org.sahni.models.responses.UserResponse;

public interface UsersService {

    public Uni<UserResponse> getUser(Long id);
    public Uni<Response> signUpUser(SignUpRequest signUpRequest);
    public Uni<UserResponse> logInUser(LogInRequest logInRequest);
}
