package org.sahni.exception.authentication;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.sahni.exception.AuthSphereException;
import org.sahni.models.responses.ErrorResponse;

import static org.sahni.exception.ErrorCodes.INTERNAL_SERVER_ERROR;

@Provider
public class AuthSphereExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        switch (exception) {
            case AuthSphereException ex -> {
                return Response
                        .status(ex.getStatusCode())
                        .entity(
                                ErrorResponse
                                        .builder()
                                        .code(ex.getCode())
                                        .message(ex.getMessage())
                                        .build())
                        .build();
            }
            default -> {
                return Response
                        .status(500)
                        .entity(
                                ErrorResponse
                                        .builder()
                                        .code(INTERNAL_SERVER_ERROR.getCode())
                                        .message(exception.getMessage())
                                        .build())
                        .build();
            }
        }
    }
}
