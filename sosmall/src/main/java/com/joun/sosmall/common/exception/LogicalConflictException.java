package com.joun.sosmall.common.exception;

public class LogicalConflictException extends Exception {
  public LogicalConflictException() {
    super("Logically conflict and unprocessable");
  }

  public LogicalConflictException(String message) {
    super(message);
  }
}
