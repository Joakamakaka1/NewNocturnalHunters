package com.newnocturnalhunter.api_rest.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Rest handler exception.
 */
@ControllerAdvice
public class RestHandlerException {
    /**
     * Not found exception error msg.
     *
     * @param req the req
     * @param ex  the ex
     * @return the error msg
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMsg notFoundException(HttpServletRequest req, NotFoundException ex) {
        return new ErrorMsg(ex.getMessage(), req.getRequestURL().toString());
    }

    /**
     * Generic exception error msg.
     *
     * @param req the req
     * @param ex  the ex
     * @return the error msg
     */
    @ExceptionHandler(GenericException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMsg genericException(HttpServletRequest req, GenericException ex) {
        return new ErrorMsg(ex.getMessage(), req.getRequestURL().toString());
    }

    /**
     * Unauthorized exception error msg.
     *
     * @param req the req
     * @param ex  the ex
     * @return the error msg
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorMsg unauthorizedException(HttpServletRequest req, UnauthorizedException ex) {
        return new ErrorMsg(ex.getMessage(), req.getRequestURL().toString());
    }

    /**
     * Forbidden exception error msg.
     *
     * @param req the req
     * @param ex  the ex
     * @return the error msg
     */
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorMsg forbiddenException(HttpServletRequest req, ForbiddenException ex) {
        return new ErrorMsg(ex.getMessage(), req.getRequestURL().toString());
    }

    /**
     * Duplicate exception error msg.
     *
     * @param req the req
     * @param ex  the ex
     * @return the error msg
     */
    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorMsg duplicateException(HttpServletRequest req, DuplicateException ex) {
        return new ErrorMsg(ex.getMessage(), req.getRequestURL().toString());
    }

    /**
     * Method not allowed exception error msg.
     *
     * @param req the req
     * @param ex  the ex
     * @return the error msg
     */
    @ExceptionHandler(MethodNotAllowedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public ErrorMsg methodNotAllowedException(HttpServletRequest req, MethodNotAllowedException ex) {
        return new ErrorMsg(ex.getMessage(), req.getRequestURL().toString());
    }

    /**
     * Validation exception error msg.
     *
     * @param req the req
     * @param ex  the ex
     * @return the error msg
     */
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMsg validationException(HttpServletRequest req, BadRequestException ex) {
        return new ErrorMsg(ex.getMessage(), req.getRequestURL().toString());
    }
}
