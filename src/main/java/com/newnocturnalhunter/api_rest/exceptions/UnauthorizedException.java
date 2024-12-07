package com.newnocturnalhunter.api_rest.exceptions;

public class UnauthorizedException extends RuntimeException{
    private static final String DESCRIPTION = "Unauthorized (401)";
    public UnauthorizedException(String message) {
        super(DESCRIPTION + ": " + message);
    }
}
