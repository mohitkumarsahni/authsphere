package org.sahni.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.sahni.models.responses.ErrorResponse;

@Provider
public class ExceptionHandler implements ExceptionMapper<AuthSphereException> {

    @Override
    public Response toResponse(AuthSphereException exception) {
        return Response
                .status(exception.getStatusCode())
                .entity(
                        ErrorResponse
                                .builder()
                                .code(exception.getCode())
                                .message(exception.getMessage())
                                .build())
                .build();
    }
}
