package com.bmo.common.auth_service.core.service;

import com.bmo.common.auth_service.core.repository.SecurityUserRepository;
import com.bmo.common.auth_service.core.dbmodel.SecurityUser;
import com.bmo.common.auth_service.model.exception.AuthServiceBusinessException;
import com.bmo.common.auth_service.model.exception.EntityNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserServiceImpl implements SecurityUserService {

  private final SecurityUserRepository securityUserRepository;

  @Override
  public SecurityUser getSecurityUserById(UUID secutityUserUuid) {
    return securityUserRepository.findById(secutityUserUuid)
        .orElseThrow(() -> new EntityNotFoundException(
            "SecurityUser with uuid {%s} not found".formatted(secutityUserUuid)));
  }

  @Override
  public SecurityUser updateUserId(UUID secutityUserUuid, UUID userId) {
    return securityUserRepository.findById(secutityUserUuid)
        .map(securityUser -> {
          if (securityUser.getUserId() != null) {
            throw new AuthServiceBusinessException("User id is already set");
          }

          securityUser.setUserId(userId);
          return securityUserRepository.save(securityUser);
        })
        .orElseThrow(() -> new EntityNotFoundException(
            "SecurityUser with uuid {%s} not found".formatted(secutityUserUuid)));
  }
}
