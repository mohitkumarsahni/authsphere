package org.sahni.exception.authentication;

import io.quarkus.security.AuthenticationFailedException;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.sahni.models.responses.ErrorResponse;

import static org.sahni.exception.ErrorCodes.UNAUTHORIZED_ACCESS;
import static org.sahni.exception.ErrorMessages.AUTHENTICATION_EXCEPTION_MESSAGE;


@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFailedExceptionMapper implements ExceptionMapper<AuthenticationFailedException> {

    @Override
    public Response toResponse(AuthenticationFailedException exception) {
        return Response
                .status(401)
                .entity(
                        ErrorResponse
                                .builder()
                                .code(UNAUTHORIZED_ACCESS.getCode())
                                .message(AUTHENTICATION_EXCEPTION_MESSAGE)
                                .build()
                )
                .build();
    }
}


//import io.quarkus.security.AuthenticationFailedException; when token is expired or invalid
//import io.quarkus.security.UnauthorizedException; when token is not provided
//import io.quarkus.security.AuthenticationCompletionException;
//import io.quarkus.security.ForbiddenException;
//import io.quarkus.security.AuthenticationRedirectException;