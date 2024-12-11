package com.newnocturnalhunter.api_rest.exceptions;

/**
 * The type Method not allowed exception.
 */
public class MethodNotAllowedException extends RuntimeException{
    private static final String DESCRIPTION = "Method not allowed (405)";

    /**
     * Instantiates a new Method not allowed exception.
     *
     * @param message the message
     */
    public MethodNotAllowedException(String message) {
        super(DESCRIPTION + ": " + message);
    }
}
