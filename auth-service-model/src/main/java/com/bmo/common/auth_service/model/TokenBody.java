package com.bmo.common.auth_service.model;

import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TokenBody {
  private UUID userId;
  private Set<String> authorities;
}
