package com.newnocturnalhunter.api_rest.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestHandlerException {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMsg notFoundException(HttpServletRequest req, NotFoundException ex) {
        return new ErrorMsg(ex.getMessage(), req.getRequestURL().toString());
    }

    @ExceptionHandler(GenericException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMsg genericException(HttpServletRequest req, GenericException ex) {
        return new ErrorMsg(ex.getMessage(), req.getRequestURL().toString());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorMsg unauthorizedException(HttpServletRequest req, UnauthorizedException ex) {
        return new ErrorMsg(ex.getMessage(), req.getRequestURL().toString());
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorMsg forbiddenException(HttpServletRequest req, ForbiddenException ex) {
        return new ErrorMsg(ex.getMessage(), req.getRequestURL().toString());
    }

    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorMsg duplicateException(HttpServletRequest req, DuplicateException ex) {
        return new ErrorMsg(ex.getMessage(), req.getRequestURL().toString());
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public ErrorMsg methodNotAllowedException(HttpServletRequest req, MethodNotAllowedException ex) {
        return new ErrorMsg(ex.getMessage(), req.getRequestURL().toString());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMsg validationException(HttpServletRequest req, BadRequestException ex) {
        return new ErrorMsg(ex.getMessage(), req.getRequestURL().toString());
    }
}
