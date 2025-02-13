package org.sahni.services.impl;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.sahni.models.db.Users;
import org.sahni.models.requests.CreateUserRequest;
import org.sahni.repositories.UsersRepository;
import org.sahni.services.UsersService;
import org.sahni.validators.UsersRequestValidator;

@ApplicationScoped
public class UsersServiceImpl implements UsersService {

    @Inject
    UsersRequestValidator usersRequestValidator;

    @Inject
    UsersRepository usersRepository;

    @Override
    public Uni<Users> getUser(Long id) {
        return usersRepository.fetchUserById(id);
    }

    @Override
    public Uni<Users> createUser(CreateUserRequest createUserRequest) {
        usersRequestValidator.validate(createUserRequest);
        return usersRepository.createUser(new Users());
    }
}
