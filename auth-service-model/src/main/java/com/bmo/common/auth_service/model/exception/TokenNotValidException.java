package com.bmo.common.auth_service.model.exception;

public class TokenNotValidException extends RuntimeException {

  public TokenNotValidException(String message) {
    super(message);
  }
}
