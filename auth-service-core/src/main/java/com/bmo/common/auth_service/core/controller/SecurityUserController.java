package com.bmo.common.auth_service.core.controller;

import com.bmo.common.auth_service.core.dbmodel.SecurityUser;
import com.bmo.common.auth_service.core.mapper.SecurityUserMapper;
import com.bmo.common.auth_service.core.service.SecurityUserService;
import com.bmo.common.auth_service.model.Authority;
import com.bmo.common.auth_service.model.SecurityUserDto;
import com.bmo.common.auth_service.model.UpdateUserIdBody;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SecurityUserController {

  private final SecurityUserService securityUserService;
  private final SecurityUserMapper securityUserMapper;

  @GetMapping("/users/{id}/authorities")
  public ResponseEntity<Set<Authority>> getSecurityUserAuthorities(@PathVariable("id") UUID secutityUserUuid) {
    Set<String> stringAuthorities = securityUserService.getSecurityUserAuthorities(secutityUserUuid);
    Set<Authority> authorities = stringAuthorities.stream()
            .map(Authority::getByStringAuthority)
            .collect(Collectors.toSet());
    return ResponseEntity.ok(authorities);
  }

  @PatchMapping("/users/{userId}/authorities/add")
  public ResponseEntity<Void> grantAuthoritiesToUser(
      @PathVariable("userId") UUID secutityUserUuid,
      @NotBlank @RequestBody Set<UUID> authorityIdsToAdd) {
    securityUserService.grantAuthoritiesToUser(secutityUserUuid, authorityIdsToAdd);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/users/{userId}/authorities/remove")
  public ResponseEntity<Void> removeAuthoritiesFromUser(
      @PathVariable("userId") UUID secutityUserUuid,
      @NotBlank @RequestBody Set<UUID> authorityIdsToRemove) {
    securityUserService.removeAuthoritiesFromUser(secutityUserUuid, authorityIdsToRemove);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/users/{userId}/authority-group/add")
  public ResponseEntity<Void> grantAuthorityGroupsToUser(
      @PathVariable("userId") UUID secutityUserUuid,
      @NotBlank @RequestBody Set<UUID> authorityGroupIdsToAdd) {
    securityUserService.grantAuthorityGroupsToUser(secutityUserUuid, authorityGroupIdsToAdd);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/users/{userId}/authority-group/remove")
  public ResponseEntity<Void> removeAuthorityGroupsFromUser(
      @PathVariable("userId") UUID secutityUserUuid,
      @NotBlank @RequestBody Set<UUID> authorityGroupIdsToRemove) {
    securityUserService.removeAuthorityGroupsFromUser(secutityUserUuid, authorityGroupIdsToRemove);
    return ResponseEntity.noContent().build();
  }

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
