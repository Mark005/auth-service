package com.bmo.common.auth_service.core.controller;

import com.bmo.common.auth_service.core.dbmodel.SecurityUser;
import com.bmo.common.auth_service.core.mapper.SecurityUserMapper;
import com.bmo.common.auth_service.core.service.SecurityUserService;
import com.bmo.common.auth_service.model.SecurityUserDto;
import com.bmo.common.auth_service.model.UpdateUserIdBody;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SecurityUserController {

  private final SecurityUserService securityUserService;
  private final SecurityUserMapper securityUserMapper;

  @GetMapping("/users/{id}")
  public ResponseEntity<SecurityUserDto> getSecurityUserInfo(@PathVariable("id") UUID secutityUserUuid) {
    SecurityUser securityUser = securityUserService.getSecurityUserById(secutityUserUuid);
    SecurityUserDto securityUserDto = securityUserMapper.map(securityUser);
    return ResponseEntity.ok(securityUserDto);
  }

  @PatchMapping("/users/{id}")
  public ResponseEntity<SecurityUserDto> updateUserId(
      @PathVariable("id") UUID secutityUserUuid,
      UpdateUserIdBody updateUserIdBody) {
    SecurityUser securityUser = securityUserService.updateUserId(secutityUserUuid, updateUserIdBody.getUserId());
    SecurityUserDto securityUserDto = securityUserMapper.map(securityUser);
    return ResponseEntity.ok(securityUserDto);
  }
}
