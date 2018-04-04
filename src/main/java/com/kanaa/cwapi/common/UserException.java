package com.kanaa.cwapi.common;

public class UserException extends Exception {

    public UserException(String message) {
        super(message);
    }

  public UserException(Throwable cause) {
    super(cause);
  }
}