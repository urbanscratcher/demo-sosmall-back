package com.joun.sosmall.common.exception;

public class InvalidRelatedDataException extends Exception {
    public InvalidRelatedDataException() {
        super("Related data is invalid or not found");
    }

    public InvalidRelatedDataException(String message) {
        super(message);
    }
}
