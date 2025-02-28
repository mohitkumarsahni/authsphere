package org.sahni.services.impl;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.sahni.commons.utils.AuthSphereUtility;
import org.sahni.commons.utils.JwtUtility;
import org.sahni.commons.utils.PasswordUtility;
import org.sahni.exception.AuthSphereException;
import org.sahni.exception.ErrorCodes;
import org.sahni.models.db.Users;
import org.sahni.models.db.UsersVerify;
import org.sahni.models.requests.LogInRequest;
import org.sahni.models.requests.SignUpRequest;
import org.sahni.models.responses.CreateUserResponse;
import org.sahni.models.responses.LogInResponse;
import org.sahni.models.responses.UserResponse;
import org.sahni.repositories.UsersRepository;
import org.sahni.repositories.UsersVerifyRepository;
import org.sahni.services.MailerService;
import org.sahni.services.UsersService;
import org.sahni.validators.UsersRequestValidator;

import java.util.Objects;
import java.util.UUID;

import static org.sahni.commons.Constants.CREATE_USER_SUCCESS_MESSAGE;
import static org.sahni.exception.ErrorMessages.INCORRECT_PASSWORD_MESSAGE;
import static org.sahni.exception.ErrorMessages.INTERNAL_SERVER_ERROR;
import static org.sahni.exception.ErrorMessages.RESOURCE_NOT_FOUND;

@ApplicationScoped
public class UsersServiceImpl implements UsersService {

    @Inject
    UsersRequestValidator usersRequestValidator;

    @Inject
    UsersRepository usersRepository;

    @Inject
    UsersVerifyRepository usersVerifyRepository;

    @Inject
    MailerService mailerService;

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
    public Uni<LogInResponse> logInUser(LogInRequest logInRequest) {
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

                    return LogInResponse
                            .builder()
                            .token(JwtUtility.createJWT(user.getId().toString()))
                            .build();
                });
    }

    @Override
    public Uni<Void> generateOTP(Long id) {
        return usersRepository
                .fetchUserById(id)
                .chain(users -> {
                    if (Objects.isNull(users)) {
                        throw new AuthSphereException(RESOURCE_NOT_FOUND, ErrorCodes.RESOURCE_NOT_FOUND.toString(), 404);
                    }
                    UsersVerify usersVerify = UsersVerify.builder()
                            .emailID(users.getEmailID())
                            .otp(UUID.randomUUID().toString())
                            .build();
                    return usersVerifyRepository
                            .persistUser(usersVerify)
                            .chain(voidResult -> {
                                return mailerService
                                        .sendUserVerificationMail(usersVerify.getEmailID(), usersVerify.getOtp());
                            })
                            .onFailure()
                            .invoke(failure -> {
                                throw new AuthSphereException(INTERNAL_SERVER_ERROR, ErrorCodes.INTERNAL_SERVER_ERROR.toString(), 500);
                            });
                });
    }

    @Override
    public Uni<Void> verifyOTP() {
        return null;
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
