package org.sahni.exception.authentication;

import io.quarkus.security.UnauthorizedException;
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
public class UnauthorizedExceptionMapper implements ExceptionMapper<UnauthorizedException> {

    @Override
    public Response toResponse(UnauthorizedException exception) {
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
