package com.joun.sosmall.common.exception;

public class DataNotFoundException extends Exception {
    public DataNotFoundException() {
        super("Data is not exist.");
    }

    public DataNotFoundException(String message) {
        super(message);
    }
}
