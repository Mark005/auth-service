package com.bmo.common.auth_service.client;

import com.bmo.common.auth_service.model.Authority;
import com.bmo.common.auth_service.model.SecurityUserDto;
import com.bmo.common.auth_service.model.TokenBody;
import com.bmo.common.auth_service.model.ValidateTokenRequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;
import java.util.UUID;

@FeignClient(name = "auth-service-client", url = "${feign.client.config.auth-service.url}")
public interface AuthServiceClient {

  @GetMapping("/users/{id}/authorities")
  Set<Authority> getSecurityUserAuthorities(@PathVariable("id") UUID secutityUserUuid);

  @PostMapping("/jwt/validate")
  TokenBody validate(@RequestBody ValidateTokenRequestBody validateTokenRequestBody);

  @GetMapping("/users/{id}")
  SecurityUserDto getSecurityUserInfo(@PathVariable("id") UUID secutityUserUuid);

}
