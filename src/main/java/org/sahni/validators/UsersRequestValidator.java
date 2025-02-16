package org.sahni.validators;

import jakarta.enterprise.context.ApplicationScoped;
import org.sahni.exception.AuthSphereException;
import org.sahni.exception.ErrorCodes;
import org.sahni.models.requests.LogInRequest;
import org.sahni.models.requests.SignUpRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.sahni.exception.ErrorMessages.BLANK_EMPTY_DOB_MESSAGE;
import static org.sahni.exception.ErrorMessages.BLANK_EMPTY_EMAIL_ID_MESSAGE;
import static org.sahni.exception.ErrorMessages.BLANK_EMPTY_FIRST_NAME_MESSAGE;
import static org.sahni.exception.ErrorMessages.BLANK_EMPTY_LAST_NAME_MESSAGE;
import static org.sahni.exception.ErrorMessages.BLANK_EMPTY_PASSWORD_MESSAGE;
import static org.sahni.exception.ErrorMessages.INVALID_DOB_MESSAGE;
import static org.sahni.exception.ErrorMessages.INVALID_EMAIL_ID_MESSAGE;
import static org.sahni.exception.ErrorMessages.REQUIRED_CHARACTERS_PASSWORD_MESSAGE;
import static org.sahni.exception.ErrorMessages.REQUIRED_DOB_MESSAGE;
import static org.sahni.exception.ErrorMessages.REQUIRED_EMAIL_ID_MESSAGE;
import static org.sahni.exception.ErrorMessages.REQUIRED_FIRST_NAME_MESSAGE;
import static org.sahni.exception.ErrorMessages.REQUIRED_LAST_NAME_MESSAGE;
import static org.sahni.exception.ErrorMessages.REQUIRED_LENGTH_PASSWORD_MESSAGE;
import static org.sahni.exception.ErrorMessages.REQUIRED_PASSWORD_MESSAGE;
import static org.sahni.exception.ErrorMessages.REQUIRED_REQUEST_BODY_MESSAGE;
import static org.sahni.exception.ErrorMessages.TOO_LARGE_FIRST_NAME_MESSAGE;
import static org.sahni.exception.ErrorMessages.TOO_LARGE_LAST_NAME_MESSAGE;
import static org.sahni.exception.ErrorMessages.TOO_SMALL_FIRST_NAME_MESSAGE;
import static org.sahni.exception.ErrorMessages.TOO_SMALL_LAST_NAME_MESSAGE;

@ApplicationScoped
public class UsersRequestValidator extends BaseValidator {

    public void validate(LogInRequest logInRequest) {
        nullCheck(logInRequest, REQUIRED_REQUEST_BODY_MESSAGE);
        nullCheck(logInRequest.getEmailID(), BLANK_EMPTY_EMAIL_ID_MESSAGE);
        nullCheck(logInRequest.getPassword(), BLANK_EMPTY_PASSWORD_MESSAGE);
    }

    public void validate(SignUpRequest signUpRequest) {
        nullCheck(signUpRequest, REQUIRED_REQUEST_BODY_MESSAGE);
        validateFirstName(signUpRequest.getFirstName());
        validateLastName(signUpRequest.getLastName());
        validateDate(signUpRequest.getDateOfBirth());
        validateEmailID(signUpRequest.getEmailID());
        validatePassword(signUpRequest.getPassword());
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

    private void validatePassword(String password) {
        nullCheck(password, REQUIRED_PASSWORD_MESSAGE);
        switch (password) {
            case String str when str.isEmpty() -> {
                throw new AuthSphereException(BLANK_EMPTY_PASSWORD_MESSAGE, ErrorCodes.BAD_REQUEST.toString(), 400);
            }
            case String str when str.isBlank() -> {
                throw new AuthSphereException(BLANK_EMPTY_PASSWORD_MESSAGE, ErrorCodes.BAD_REQUEST.toString(), 400);
            }
            default -> {
                if (password.length() < 10) {
                    throw new AuthSphereException(REQUIRED_LENGTH_PASSWORD_MESSAGE, ErrorCodes.BAD_REQUEST.toString(), 400);
                }
                String regex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$]).{10,}$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(password);
                if (!matcher.matches()) {
                    throw new AuthSphereException(REQUIRED_CHARACTERS_PASSWORD_MESSAGE, ErrorCodes.BAD_REQUEST.toString(), 400);
                }
            }
        }
    }
}
