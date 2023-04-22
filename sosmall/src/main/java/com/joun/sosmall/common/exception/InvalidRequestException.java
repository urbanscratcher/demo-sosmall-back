package com.joun.sosmall.common.exception;

public class InvalidRequestException extends Exception {
    public InvalidRequestException() {
        super("Request is invalid");
    }

    public InvalidRequestException(String message) {
        super(message);
    }
}
