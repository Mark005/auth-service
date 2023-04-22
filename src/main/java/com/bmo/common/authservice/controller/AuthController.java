package com.bmo.common.authservice.controller;

import com.bmo.common.authservice.model.AuthToken;
import com.bmo.common.authservice.model.LoginRequestBody;
import com.bmo.common.authservice.model.RegisterRequestBody;
import com.bmo.common.authservice.model.TokenBody;
import com.bmo.common.authservice.model.ValidateTokenRequestBody;
import com.bmo.common.authservice.service.AuthService;
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
