package com.bmo.common.authservice.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class SecurityUserDto {
  private UUID id;
  private String name;
  private String surname;
  private String email;
}
