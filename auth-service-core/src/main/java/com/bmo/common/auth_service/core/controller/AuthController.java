package com.bmo.common.auth_service.core.controller;

import com.bmo.common.auth_service.core.service.AuthService;
import com.bmo.common.auth_service.model.AuthToken;
import com.bmo.common.auth_service.model.LoginRequestBody;
import com.bmo.common.auth_service.model.RegisterRequestBody;
import com.bmo.common.auth_service.model.TokenBody;
import com.bmo.common.auth_service.model.ValidateTokenRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<AuthToken> register(@RequestBody RegisterRequestBody registerRequestBody) {

    AuthToken authToken = authService.register(registerRequestBody);
    return ResponseEntity.ok(authToken);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthToken> login(@RequestBody LoginRequestBody loginRequestBody) {

    AuthToken authToken = authService.login(loginRequestBody);
    return ResponseEntity.ok(authToken);
  }

  @PostMapping("/jwt/validate")
  public ResponseEntity<TokenBody> validate(@RequestBody ValidateTokenRequestBody validateTokenRequestBody) {

    TokenBody tokenBody = authService.validate(validateTokenRequestBody);
    return ResponseEntity.ok(tokenBody);
  }

}
