package com.bmo.common.auth_service.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class AuthorityGroupDto {

  private UUID id;

  private GroupTagDto groupTag;

  private String name;

  private String description;
}
