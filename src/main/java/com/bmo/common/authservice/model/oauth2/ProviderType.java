package com.bmo.common.authservice.model.oauth2;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProviderType {
  GITHUB("github");

  private String id;

  ProviderType getById(String id) {
    for (ProviderType value : values()) {
      if (value.getId().equals(id)) {
        return value;
      }
    }
    throw new RuntimeException("ProviderType with id '%s' not found".formatted(id));
  }
}
