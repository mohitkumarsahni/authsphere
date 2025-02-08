package org.sahni.services;

import io.smallrye.mutiny.Uni;
import org.sahni.models.db.Users;
import org.sahni.models.requests.CreateUserRequest;

public interface UsersService {

    public Uni<Users> getUser(Long id);
    public Uni<Users> saveUser(CreateUserRequest createUserRequest);
}
