package com.bmo.common.authservice.controller;

import com.bmo.common.authservice.dbmodel.SecurityUser;
import com.bmo.common.authservice.mapper.SecurityUserMapper;
import com.bmo.common.authservice.model.AuthToken;
import com.bmo.common.authservice.model.LoginRequestBody;
import com.bmo.common.authservice.model.RegisterRequestBody;
import com.bmo.common.authservice.model.SecurityUserDto;
import com.bmo.common.authservice.model.TokenBody;
import com.bmo.common.authservice.model.ValidateTokenRequestBody;
import com.bmo.common.authservice.service.AuthService;
import com.bmo.common.authservice.service.SecurityUserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;
  private final SecurityUserService securityUserService;
  private final SecurityUserMapper securityUserMapper;

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

  @GetMapping("/users/{id}")
  public ResponseEntity<SecurityUserDto> getSecurityUserInfo(@PathVariable("id") UUID secutityUserUuid) {
    SecurityUser securityUser = securityUserService.getSecurityUserById(secutityUserUuid);
    SecurityUserDto securityUserDto = securityUserMapper.map(securityUser);
    return ResponseEntity.ok(securityUserDto);
  }
}
