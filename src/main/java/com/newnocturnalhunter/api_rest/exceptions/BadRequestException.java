package com.newnocturnalhunter.api_rest.exceptions;

/**
 * The type Bad request exception.
 */
public class BadRequestException extends RuntimeException{
    private static final String DESCRIPTION = "Bad request (400)";

    /**
     * Instantiates a new Bad request exception.
     *
     * @param message the message
     */
    public BadRequestException(String message) {
        super(DESCRIPTION + ": " + message);
    }
}
