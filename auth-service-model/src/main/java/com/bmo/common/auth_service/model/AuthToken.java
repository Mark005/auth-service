package com.bmo.common.auth_service.model;

import java.time.ZonedDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthToken {

  private String token;
  private ZonedDateTime expiresAt;
  private Set<String> authorities;

}
