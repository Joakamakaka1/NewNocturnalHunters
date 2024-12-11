package com.newnocturnalhunter.api_rest.exceptions;

/**
 * The type Generic exception.
 */
public class GenericException extends RuntimeException{
    private static final String DESCRIPTION = "Generic error (500)";

    /**
     * Instantiates a new Generic exception.
     *
     * @param message the message
     */
    public GenericException(String message) {
        super(DESCRIPTION + ": " + message);
    }
}
