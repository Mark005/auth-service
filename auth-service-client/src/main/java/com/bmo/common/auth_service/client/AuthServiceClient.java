package com.bmo.common.auth_service.client;

import com.bmo.common.auth_service.model.SecurityUserDto;
import com.bmo.common.auth_service.model.TokenBody;
import com.bmo.common.auth_service.model.ValidateTokenRequestBody;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-service-client", url = "${feign.client.config.auth-service.url}")
public interface AuthServiceClient {

  @PostMapping("/jwt/validate")
  ResponseEntity<TokenBody> validate(@RequestBody ValidateTokenRequestBody validateTokenRequestBody);

  @GetMapping("/users/{id}")
  ResponseEntity<SecurityUserDto> getSecurityUserInfo(@PathVariable("id") UUID secutityUserUuid);

}
