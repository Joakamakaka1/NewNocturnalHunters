package com.newnocturnalhunter.api_rest.exceptions;

public class BadRequestException extends RuntimeException{
    private static final String DESCRIPTION = "Bad request (400)";
    public BadRequestException(String message) {
        super(DESCRIPTION + ": " + message);
    }
}
