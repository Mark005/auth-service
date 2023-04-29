package com.bmo.common.auth_service.core.service;

import com.bmo.common.auth_service.core.repository.SecurityUserRepository;
import com.bmo.common.auth_service.core.dbmodel.SecurityUser;
import com.bmo.common.auth_service.model.exception.EntityNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserServiceImpl implements SecurityUserService {

  private final SecurityUserRepository securityUserRepository;

  @Override
  public SecurityUser getSecurityUserById(UUID securityUserId) {
    return securityUserRepository.findById(securityUserId)
        .orElseThrow(() -> new EntityNotFoundException(
            "SecurityUser with id {%s} not found".formatted(securityUserId)));
  }
}
