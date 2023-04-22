package com.joun.sosmall.common.exception;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ExceptionController {

    // 400
    @ExceptionHandler({ MissingServletRequestParameterException.class, InvalidRequestException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> badRequestException(HttpServletRequest request, Exception e) {
        return getDefaultReturnData(request, e, HttpStatus.BAD_REQUEST);
    }

    // 401
    @ExceptionHandler({ UnauthenticatedException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> authFailException(HttpServletRequest request,
            Exception e) {
        return getDefaultReturnData(request, e, HttpStatus.UNAUTHORIZED);
    }

    // 404
    @ExceptionHandler({ NoHandlerFoundException.class, NotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> notFoundException(HttpServletRequest request, Exception e) {
        return getDefaultReturnData(request, e, HttpStatus.NOT_FOUND);
    }

    // 409
    @ExceptionHandler({ InvalidParameterException.class, DuplicatedException.class, LogicalConflictException.class })
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> conflictException(HttpServletRequest request, Exception e) {
        return getDefaultReturnData(request, e, HttpStatus.CONFLICT);
    }

    // 500
    @ExceptionHandler({ Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> internalException(final Exception e, HttpServletRequest request) {
        e.printStackTrace();
        return getDefaultReturnData(request, e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> getDefaultReturnData(HttpServletRequest request, Exception e, HttpStatus status) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("timestamp", new Date());
        result.put("status", status.value());
        result.put("message", e.getMessage());
        result.put("path", request.getRequestURI());

        return result;
    }
}
