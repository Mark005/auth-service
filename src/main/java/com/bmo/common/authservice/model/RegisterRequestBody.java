package com.bmo.common.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegisterRequestBody {
  private String login;
  private String password;
  private String email;
  private String name;
  private String surname;
}
