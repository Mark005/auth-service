package com.bmo.common.auth_service.model;

import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class SecurityUserDto {
  private UUID securityUserId;
  private UUID userId;
  private String name;
  private String surname;
  private String email;
  private Set<AuthorityGroupDto> authorityGroups;
  private Set<AuthorityDto> authorities;
}
