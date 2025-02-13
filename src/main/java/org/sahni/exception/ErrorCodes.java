package org.sahni.exception;

public enum ErrorCodes {
    UNAUTHORIZED_ACCESS("Unauthorized access"),
    RESOURCE_NOT_FOUND("Resource not found"),
    BAD_REQUEST("Bad request");

    private String code;

    ErrorCodes(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
