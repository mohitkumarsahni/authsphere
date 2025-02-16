package org.sahni.services.impl;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.sahni.commons.utils.AuthSphereUtility;
import org.sahni.commons.utils.PasswordUtility;
import org.sahni.exception.AuthSphereException;
import org.sahni.exception.ErrorCodes;
import org.sahni.models.db.Users;
import org.sahni.models.requests.LogInRequest;
import org.sahni.models.requests.SignUpRequest;
import org.sahni.models.responses.CreateUserResponse;
import org.sahni.models.responses.UserResponse;
import org.sahni.repositories.UsersRepository;
import org.sahni.services.UsersService;
import org.sahni.validators.UsersRequestValidator;

import java.util.Objects;

import static org.sahni.commons.Constants.CREATE_USER_SUCCESS_MESSAGE;
import static org.sahni.exception.ErrorMessages.INCORRECT_PASSWORD_MESSAGE;

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
    public Uni<Response> signUpUser(SignUpRequest signUpRequest) {
        usersRequestValidator.validate(signUpRequest);
        return usersRepository
                .persistUser(convertToModel(signUpRequest))
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

    @Override
    public Uni<UserResponse> logInUser(LogInRequest logInRequest) {
        usersRequestValidator.validate(logInRequest);
        return usersRepository
                .fetchUserByEmailId(logInRequest.getEmailID())
                .onItem()
                .transform(user -> {
                    if (Objects.isNull(user)) {
                        throw new AuthSphereException(INCORRECT_PASSWORD_MESSAGE, ErrorCodes.UNAUTHORIZED_ACCESS.toString(), 401);
                    }

                    if (!PasswordUtility.checkPassword(logInRequest.getPassword(), user.getPassword())) {
                        throw new AuthSphereException(INCORRECT_PASSWORD_MESSAGE, ErrorCodes.UNAUTHORIZED_ACCESS.toString(), 401);
                    };

                    return UserResponse
                            .builder()
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .dateOfBirth(user.getDateOfBirth().toString())
                            .emailID(user.getEmailID())
                            .build();
                });
    }

    private Users convertToModel(SignUpRequest signUpRequest) {
        return Users
                .builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .dateOfBirth(AuthSphereUtility.convertToDate(signUpRequest.getDateOfBirth()))
                .emailID(signUpRequest.getEmailID())
                .isActive(true)
                .isEmailValidated(false)
                .password(PasswordUtility.hashPassword(signUpRequest.getPassword()))
                .build();
    }
}
