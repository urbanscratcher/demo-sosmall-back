package com.joun.sosmall.common.exception;

public class DuplicatedException extends Exception {
    public DuplicatedException() {
        super("Duplicated data in db");
    }

    public DuplicatedException(String message) {
        super(message);
    }
}
