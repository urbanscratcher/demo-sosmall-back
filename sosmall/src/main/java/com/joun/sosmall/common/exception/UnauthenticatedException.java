package com.joun.sosmall.common.exception;

public class UnauthenticatedException extends Exception {
  public UnauthenticatedException() {
    super("Request is invalid");
  }

  public UnauthenticatedException(String message) {
    super(message);
  }
}
