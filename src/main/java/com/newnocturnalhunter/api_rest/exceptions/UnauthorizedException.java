package com.newnocturnalhunter.api_rest.exceptions;

/**
 * The type Unauthorized exception.
 */
public class UnauthorizedException extends RuntimeException{
    private static final String DESCRIPTION = "Unauthorized (401)";

    /**
     * Instantiates a new Unauthorized exception.
     *
     * @param message the message
     */
    public UnauthorizedException(String message) {
        super(DESCRIPTION + ": " + message);
    }
}
