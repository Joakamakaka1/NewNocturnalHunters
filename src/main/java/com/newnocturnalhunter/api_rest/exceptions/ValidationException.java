package com.newnocturnalhunter.api_rest.exceptions;

public class ValidationException extends RuntimeException{
    private static final String DESCRIPTION = "Validation error (400)";
    public ValidationException(String message) {
        super(DESCRIPTION + ": " + message);
    }
}
