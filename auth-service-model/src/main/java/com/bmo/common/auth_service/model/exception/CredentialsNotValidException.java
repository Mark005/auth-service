package com.bmo.common.auth_service.model.exception;

public class CredentialsNotValidException extends RuntimeException {

  public CredentialsNotValidException(String message) {
    super(message);
  }
}
