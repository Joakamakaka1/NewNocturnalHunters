package com.newnocturnalhunter.api_rest.exceptions;

/**
 * The type Forbidden exception.
 */
public class ForbiddenException extends RuntimeException{
    private static final String DESCRIPTION = "Forbidden (403)";

    /**
     * Instantiates a new Forbidden exception.
     *
     * @param message the message
     */
    public ForbiddenException(String message) {
        super(DESCRIPTION + ": " + message);
    }
}
