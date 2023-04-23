package com.bmo.common.authservice.service;

import com.bmo.common.authservice.dbmodel.SecurityUser;
import com.bmo.common.authservice.exception.EntityNotFoundException;
import com.bmo.common.authservice.repository.SecurityUserRepository;
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
