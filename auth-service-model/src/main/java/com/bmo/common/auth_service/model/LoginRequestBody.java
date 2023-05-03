package com.bmo.common.auth_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LoginRequestBody {

  private String login;
  private String password;
}
