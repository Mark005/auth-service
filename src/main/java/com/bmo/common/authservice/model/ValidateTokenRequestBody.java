package com.bmo.common.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ValidateTokenRequestBody {
  private String token;
}
