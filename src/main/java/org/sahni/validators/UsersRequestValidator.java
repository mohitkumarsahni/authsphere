package org.sahni.validators;

import jakarta.enterprise.context.ApplicationScoped;
import org.sahni.exception.AuthSphereException;
import org.sahni.exception.ErrorCodes;
import org.sahni.exception.ErrorMessages;
import org.sahni.models.requests.CreateUserRequest;

@ApplicationScoped
public class UsersRequestValidator extends BaseValidator {

    public void validate(CreateUserRequest createUserRequest) {
        nullCheck(createUserRequest);   //request body can not be null;
        validateName(createUserRequest.getFirstName());
        validateName(createUserRequest.getLastName());
    }

    private void nullCheck(Object object) {
        if (object == null) {
            throw new AuthSphereException(ErrorMessages.REQUIRED_REQUEST_BODY_MESSAGE, ErrorCodes.BAD_REQUEST.getCode(), 400);
        }
    }

    private void validateName(String value) {
        nullCheck(value);
        switch (value) {
            case String str when str.isEmpty() -> {
                throw new AuthSphereException(ErrorCodes.BAD_REQUEST.toString(), ErrorCodes.BAD_REQUEST.toString(), 400);
            }
            case String str when str.isBlank() -> {
                throw new AuthSphereException(ErrorCodes.BAD_REQUEST.toString(), ErrorCodes.BAD_REQUEST.toString(), 400);
            }
            case String str when str.length() > 25 -> {
                throw new AuthSphereException(ErrorCodes.BAD_REQUEST.toString(), ErrorCodes.BAD_REQUEST.toString(), 400);
            }
            case String str when str.length() < 3 -> {
                throw new AuthSphereException(ErrorCodes.BAD_REQUEST.toString(), ErrorCodes.BAD_REQUEST.toString(), 400);
            }
            default -> {}
        }
    }

    private void validateDate(String value) {}

    private void validateEmailID(String value) {}
}
