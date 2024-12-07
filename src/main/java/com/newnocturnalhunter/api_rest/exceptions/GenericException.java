package com.newnocturnalhunter.api_rest.exceptions;

public class GenericException extends RuntimeException{
    private static final String DESCRIPTION = "Generic error (500)";
    public GenericException(String message) {
        super(DESCRIPTION + ": " + message);
    }
}
