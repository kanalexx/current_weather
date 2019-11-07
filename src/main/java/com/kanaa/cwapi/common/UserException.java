package com.kanaa.cwapi.common;

public class UserException extends Exception {

  public UserException(String message) {
    super(message);
  }

  public UserException(String s, Throwable cause) {
    super(cause);
  }
}