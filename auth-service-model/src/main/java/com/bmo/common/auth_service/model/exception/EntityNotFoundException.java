package com.bmo.common.auth_service.model.exception;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException(String entity, UUID id) {
    super("%s with id {%s} not found".formatted(entity, id));
  }

  public EntityNotFoundException(String message) {
    super(message);
  }
}
