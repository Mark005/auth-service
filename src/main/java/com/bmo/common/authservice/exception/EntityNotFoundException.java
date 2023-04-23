package com.bmo.common.authservice.exception;

public class EntityNotFoundException extends RuntimeException{

  public EntityNotFoundException(String message) {
    super(message);
  }
}
