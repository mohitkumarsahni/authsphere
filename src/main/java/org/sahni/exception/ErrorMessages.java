package org.sahni.exception;

public class ErrorMessages {
    public static final String REQUIRED_REQUEST_BODY_MESSAGE = "Request body is null. Please provide request body.";

    public static final String REQUIRED_FIRST_NAME_MESSAGE = "First name is required.";
    public static final String BLANK_EMPTY_FIRST_NAME_MESSAGE = "First name can not be empty or blank.";
    public static final String TOO_LARGE_FIRST_NAME_MESSAGE = "First name length is greater than 25.";
    public static final String TOO_SMALL_FIRST_NAME_MESSAGE = "First name length is less then 3.";

    public static final String REQUIRED_LAST_NAME_MESSAGE = "Last name is required.";
    public static final String BLANK_EMPTY_LAST_NAME_MESSAGE = "Last name can not be empty or blank.";
    public static final String TOO_LARGE_LAST_NAME_MESSAGE = "Last name length is greater than 25.";
    public static final String TOO_SMALL_LAST_NAME_MESSAGE = "Last name length is less then 3.";

    public static final String REQUIRED_DOB_MESSAGE = "Date of birth is required.";
    public static final String BLANK_EMPTY_DOB_MESSAGE = "Date of birth can not be empty or blank.";
    public static final String INVALID_DOB_MESSAGE = "Date of birth is invalid. Please provide a valid date in format YYYY-MM-DD.";


    public static final String REQUIRED_EMAIL_ID_MESSAGE = "Email ID is required.";
    public static final String BLANK_EMPTY_EMAIL_ID_MESSAGE = "Email ID can not be empty or blank.";
    public static final String INVALID_EMAIL_ID_MESSAGE = "Email ID is invalid. Please provide a valid email id.";

    public static final String REQUIRED_PASSWORD_MESSAGE = "Password is required.";
    public static final String BLANK_EMPTY_PASSWORD_MESSAGE = "Password can not be empty or blank.";
    public static final String REQUIRED_LENGTH_PASSWORD_MESSAGE = "Password should be minimum of 10 characters.";
    public static final String REQUIRED_CHARACTERS_PASSWORD_MESSAGE = "Password should be an alpha numeric with 1 special characters from @, #, $.";

    public static final String INCORRECT_PASSWORD_MESSAGE = "Incorrect credentials.";
    public static final String AUTHENTICATION_EXCEPTION_MESSAGE = "Unauthorized access blocked. Please try login again or refresh token.";

    public static final String RESOURCE_NOT_FOUND = "Unable to find the resource.";
    public static final String INTERNAL_SERVER_ERROR = "Something unexpected happen. Please try again.";
}
