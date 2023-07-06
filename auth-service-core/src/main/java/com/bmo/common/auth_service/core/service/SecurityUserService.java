package com.bmo.common.auth_service.core.service;

import com.bmo.common.auth_service.core.dbmodel.SecurityUser;

import java.util.Set;
import java.util.UUID;

public interface SecurityUserService {

  SecurityUser getSecurityUserById(UUID securityUserId);

  SecurityUser updateUserId(UUID secutityUserUuid, UUID userId);

  Set<String> getSecurityUserAuthorities(UUID secutityUserUuid);

  void grantAuthoritiesToUser(UUID currentSecurityUserId, UUID secutityUserUuid, Set<UUID> authorityIdsToAdd);

  void removeAuthoritiesFromUser(UUID currentSecurityUserId, UUID secutityUserUuid, Set<UUID> authorityIdsToRemove);

  void grantAuthorityGroupsToUser(UUID currentSecurityUserId, UUID secutityUserUuid, Set<UUID> authorityGroupIdsToAdd);

  void removeAuthorityGroupsFromUser(UUID currentSecurityUserId, UUID secutityUserUuid, Set<UUID> authorityGroupIdsToRemove);
}
