package org.sahni.services;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.core.Response;
import org.sahni.models.requests.LogInRequest;
import org.sahni.models.requests.SignUpRequest;
import org.sahni.models.responses.LogInResponse;
import org.sahni.models.responses.UserResponse;

public interface UsersService {

    public Uni<UserResponse> getUser(Long id);
    public Uni<Response> signUpUser(SignUpRequest signUpRequest);
    public Uni<LogInResponse> logInUser(LogInRequest logInRequest);
    public Uni<Void> generateOTP(Long id);
    public Uni<Void> verifyOTP();
}
