package org.sahni.services;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.core.Response;
import org.sahni.models.requests.CreateUserRequest;
import org.sahni.models.responses.UserResponse;

public interface UsersService {

    public Uni<UserResponse> getUser(Long id);
    public Uni<Response> createUser(CreateUserRequest createUserRequest);
}
