package org.sahni.services.impl;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.sahni.commons.utils.AuthSphereUtility;
import org.sahni.commons.utils.PasswordUtility;
import org.sahni.models.db.Users;
import org.sahni.models.requests.CreateUserRequest;
import org.sahni.models.responses.CreateUserResponse;
import org.sahni.models.responses.UserResponse;
import org.sahni.repositories.UsersRepository;
import org.sahni.services.UsersService;
import org.sahni.validators.UsersRequestValidator;

import static org.sahni.commons.Constants.CREATE_USER_SUCCESS_MESSAGE;

@ApplicationScoped
public class UsersServiceImpl implements UsersService {

    @Inject
    UsersRequestValidator usersRequestValidator;

    @Inject
    UsersRepository usersRepository;

    @Override
    public Uni<UserResponse> getUser(Long id) {
        return usersRepository
                .fetchUserById(id)
                .onItem()
                .transform(user -> {
                    return UserResponse
                            .builder()
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .dateOfBirth(user.getDateOfBirth().toString())
                            .emailID(user.getEmailID())
                            .build();
                });
    }

    @Override
    public Uni<Response> createUser(CreateUserRequest createUserRequest) {
        usersRequestValidator.validate(createUserRequest);
        return usersRepository
                .createUser(convertToModel(createUserRequest))
                .onItem()
                .transform(user -> {
                    return Response
                            .status(201)
                            .entity(
                                    CreateUserResponse
                                            .builder()
                                            .message(CREATE_USER_SUCCESS_MESSAGE)
                                            .build()
                            )
                            .build();
                });
    }

    private Users convertToModel(CreateUserRequest createUserRequest) {
        return Users
                .builder()
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .dateOfBirth(AuthSphereUtility.convertToDate(createUserRequest.getDateOfBirth()))
                .emailID(createUserRequest.getEmailID())
                .isActive(true)
                .isEmailValidated(false)
                .password(PasswordUtility.hashPassword(createUserRequest.getPassword()))
                .build();
    }
}
