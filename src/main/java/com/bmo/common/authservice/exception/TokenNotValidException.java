package com.bmo.common.authservice.exception;

public class TokenNotValidException extends RuntimeException {

  public TokenNotValidException(String message) {
    super(message);
  }
}
