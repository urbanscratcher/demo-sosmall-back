package com.joun.sosmall.common.exception;

public class NotFoundException extends Exception {
    public NotFoundException() {
        super("Data is not exist.");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
