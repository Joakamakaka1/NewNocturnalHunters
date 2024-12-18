package com.newnocturnalhunter.api_rest.exceptions;

/**
 * The type Duplicate exception.
 */
public class DuplicateException extends RuntimeException {
    private static final String DESCRIPTION = "Duplicate (409)";

    /**
     * Instantiates a new Duplicate exception.
     *
     * @param message the message
     */
    public DuplicateException(String message) {
        super(DESCRIPTION + ": " + message);
    }
}
