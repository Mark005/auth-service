package com.bmo.common.auth_service.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class AuthorityDto {

  private UUID id;

  private String authority;

  private String description;
}
