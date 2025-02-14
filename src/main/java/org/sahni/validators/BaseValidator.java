package org.sahni.validators;

import org.apache.commons.validator.routines.EmailValidator;
import org.sahni.exception.AuthSphereException;
import org.sahni.exception.ErrorCodes;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public abstract class BaseValidator {

    public void nullCheck(Object object, String message) {
        if (object == null) {
            throw new AuthSphereException(message, ErrorCodes.BAD_REQUEST.getCode(), 400);
        }
    }

    public boolean isValidDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public boolean isValidEmailAddress(String value) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(value);
    }
}
