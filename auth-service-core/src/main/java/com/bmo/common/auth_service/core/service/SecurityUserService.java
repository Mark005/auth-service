package com.bmo.common.auth_service.core.service;

import com.bmo.common.auth_service.core.dbmodel.SecurityUser;

import java.util.Set;
import java.util.UUID;

public interface SecurityUserService {

  SecurityUser getSecurityUserById(UUID securityUserId);

  SecurityUser updateUserId(UUID secutityUserUuid, UUID userId);

  Set<String> getSecurityUserAuthorities(UUID secutityUserUuid);
}
