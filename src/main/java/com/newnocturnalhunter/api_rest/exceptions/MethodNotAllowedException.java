package com.newnocturnalhunter.api_rest.exceptions;

public class MethodNotAllowedException extends RuntimeException{
    private static final String DESCRIPTION = "Method not allowed (405)";
    public MethodNotAllowedException(String message) {
        super(DESCRIPTION + ": " + message);
    }
}
