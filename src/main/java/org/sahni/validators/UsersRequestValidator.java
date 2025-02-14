package org.sahni.validators;

import jakarta.enterprise.context.ApplicationScoped;
import org.sahni.exception.AuthSphereException;
import org.sahni.exception.ErrorCodes;
import org.sahni.models.requests.CreateUserRequest;

import static org.sahni.exception.ErrorMessages.BLANK_EMPTY_DOB_MESSAGE;
import static org.sahni.exception.ErrorMessages.BLANK_EMPTY_EMAIL_ID_MESSAGE;
import static org.sahni.exception.ErrorMessages.BLANK_EMPTY_FIRST_NAME_MESSAGE;
import static org.sahni.exception.ErrorMessages.BLANK_EMPTY_LAST_NAME_MESSAGE;
import static org.sahni.exception.ErrorMessages.INVALID_DOB_MESSAGE;
import static org.sahni.exception.ErrorMessages.INVALID_EMAIL_ID_MESSAGE;
import static org.sahni.exception.ErrorMessages.REQUIRED_DOB_MESSAGE;
import static org.sahni.exception.ErrorMessages.REQUIRED_EMAIL_ID_MESSAGE;
import static org.sahni.exception.ErrorMessages.REQUIRED_FIRST_NAME_MESSAGE;
import static org.sahni.exception.ErrorMessages.REQUIRED_LAST_NAME_MESSAGE;
import static org.sahni.exception.ErrorMessages.REQUIRED_REQUEST_BODY_MESSAGE;
import static org.sahni.exception.ErrorMessages.TOO_LARGE_FIRST_NAME_MESSAGE;
import static org.sahni.exception.ErrorMessages.TOO_LARGE_LAST_NAME_MESSAGE;
import static org.sahni.exception.ErrorMessages.TOO_SMALL_FIRST_NAME_MESSAGE;
import static org.sahni.exception.ErrorMessages.TOO_SMALL_LAST_NAME_MESSAGE;

@ApplicationScoped
public class UsersRequestValidator extends BaseValidator {

    public void validate(CreateUserRequest createUserRequest) {
        nullCheck(createUserRequest, REQUIRED_REQUEST_BODY_MESSAGE);
        validateFirstName(createUserRequest.getFirstName());
        validateLastName(createUserRequest.getLastName());
        validateDate(createUserRequest.getDateOfBirth());
        validateEmailID(createUserRequest.getEmailID());
    }

    private void validateFirstName(String value) {
        nullCheck(value, REQUIRED_FIRST_NAME_MESSAGE);
        switch (value) {
            case String str when str.isEmpty() -> {
                throw new AuthSphereException(BLANK_EMPTY_FIRST_NAME_MESSAGE, ErrorCodes.BAD_REQUEST.toString(), 400);
            }
            case String str when str.isBlank() -> {
                throw new AuthSphereException(BLANK_EMPTY_FIRST_NAME_MESSAGE, ErrorCodes.BAD_REQUEST.toString(), 400);
            }
            case String str when str.length() > 25 -> {
                throw new AuthSphereException(TOO_LARGE_FIRST_NAME_MESSAGE, ErrorCodes.BAD_REQUEST.toString(), 400);
            }
            case String str when str.length() < 3 -> {
                throw new AuthSphereException(TOO_SMALL_FIRST_NAME_MESSAGE, ErrorCodes.BAD_REQUEST.toString(), 400);
            }
            default -> {}
        }
    }

    private void validateLastName(String value) {
        nullCheck(value, REQUIRED_LAST_NAME_MESSAGE);
        switch (value) {
            case String str when str.isEmpty() -> {
                throw new AuthSphereException(BLANK_EMPTY_LAST_NAME_MESSAGE, ErrorCodes.BAD_REQUEST.toString(), 400);
            }
            case String str when str.isBlank() -> {
                throw new AuthSphereException(BLANK_EMPTY_LAST_NAME_MESSAGE, ErrorCodes.BAD_REQUEST.toString(), 400);
            }
            case String str when str.length() > 25 -> {
                throw new AuthSphereException(TOO_LARGE_LAST_NAME_MESSAGE, ErrorCodes.BAD_REQUEST.toString(), 400);
            }
            case String str when str.length() < 3 -> {
                throw new AuthSphereException(TOO_SMALL_LAST_NAME_MESSAGE, ErrorCodes.BAD_REQUEST.toString(), 400);
            }
            default -> {}
        }
    }

    private void validateDate(String value) {
        nullCheck(value, REQUIRED_DOB_MESSAGE);
        switch (value) {
            case String str when str.isEmpty() -> {
                throw new AuthSphereException(BLANK_EMPTY_DOB_MESSAGE, ErrorCodes.BAD_REQUEST.toString(), 400);
            }
            case String str when str.isBlank() -> {
                throw new AuthSphereException(BLANK_EMPTY_DOB_MESSAGE, ErrorCodes.BAD_REQUEST.toString(), 400);
            }
            case String str when !isValidDate(str) -> {
                throw new AuthSphereException(INVALID_DOB_MESSAGE, ErrorCodes.BAD_REQUEST.toString(), 400);
            }
            default -> {}
        }
    }

    private void validateEmailID(String value) {
        nullCheck(value, REQUIRED_EMAIL_ID_MESSAGE);
        switch (value) {
            case String str when str.isEmpty() -> {
                throw new AuthSphereException(BLANK_EMPTY_EMAIL_ID_MESSAGE, ErrorCodes.BAD_REQUEST.toString(), 400);
            }
            case String str when str.isBlank() -> {
                throw new AuthSphereException(BLANK_EMPTY_EMAIL_ID_MESSAGE, ErrorCodes.BAD_REQUEST.toString(), 400);
            }
            case String str when !isValidEmailAddress(str) -> {
                throw new AuthSphereException(INVALID_EMAIL_ID_MESSAGE, ErrorCodes.BAD_REQUEST.toString(), 400);
            }
            default -> {}
        }
    }
}
