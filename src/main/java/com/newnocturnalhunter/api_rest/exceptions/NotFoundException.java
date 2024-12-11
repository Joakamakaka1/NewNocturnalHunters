package com.newnocturnalhunter.api_rest.exceptions;

/**
 * The type Not found exception.
 */
public class NotFoundException extends RuntimeException{
    private static final String DESCRIPTION = "Not found (404)";

    /**
     * Instantiates a new Not found exception.
     *
     * @param message the message
     */
    public NotFoundException(String message) {
        super(DESCRIPTION + ": " + message);
    }
}
