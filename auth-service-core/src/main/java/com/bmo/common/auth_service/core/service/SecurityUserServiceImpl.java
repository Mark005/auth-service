package com.bmo.common.auth_service.core.service;

import com.bmo.common.auth_service.core.dbmodel.Authority;
import com.bmo.common.auth_service.core.dbmodel.AuthorityGroup;
import com.bmo.common.auth_service.core.dbmodel.SecurityUser;
import com.bmo.common.auth_service.core.repository.AuthorityGroupRepository;
import com.bmo.common.auth_service.core.repository.AuthorityRepository;
import com.bmo.common.auth_service.core.repository.SecurityUserRepository;
import com.bmo.common.auth_service.model.exception.AuthServiceBusinessException;
import com.bmo.common.auth_service.model.exception.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserServiceImpl implements SecurityUserService {

  private final SecurityUserRepository securityUserRepository;
  private final AuthorityRepository authorityRepository;
  private final AuthorityGroupRepository authorityGroupRepository;

  @Override
  public SecurityUser getSecurityUserById(UUID secutityUserUuid) {
    return securityUserRepository.findById(secutityUserUuid)
        .orElseThrow(() -> new EntityNotFoundException("SecurityUser", secutityUserUuid));
  }

  @Override
  public SecurityUser updateUserId(UUID secutityUserUuid, UUID userId) {
    SecurityUser securityUser = getSecurityUserById(secutityUserUuid);

    if (securityUser.getUserId() != null) {
      throw new AuthServiceBusinessException("User id is already set");
    }

    securityUser.setUserId(userId);
    return securityUserRepository.save(securityUser);
  }

  @Override
  public Set<String> getSecurityUserAuthorities(UUID secutityUserUuid) {
    return authorityRepository.findAllAuthoritiesBySecurityUserId(secutityUserUuid);
  }

  @Override
  public void grantAuthoritiesToUser(UUID secutityUserUuid, Set<UUID> authorityIdsToAdd) {
    SecurityUser securityUser = getSecurityUserById(secutityUserUuid);

    List<Authority> authoritiesToAdd = authorityIdsToAdd.stream()
        .map(uuid -> Authority.builder()
            .id(uuid)
            .build())
        .toList();
    securityUser.getAuthorities().addAll(authoritiesToAdd);

    securityUserRepository.save(securityUser);
  }

  @Override
  public void removeAuthoritiesFromUser(UUID secutityUserUuid, Set<UUID> authorityIdsToRemove) {
    SecurityUser securityUser = getSecurityUserById(secutityUserUuid);

    securityUser.getAuthorities()
        .removeIf(authority -> authorityIdsToRemove.contains(authority.getId()));

    securityUserRepository.save(securityUser);
  }

  @Override
  public void grantAuthorityGroupsToUser(UUID secutityUserUuid, Set<UUID> authorityGroupIdsToAdd) {
    SecurityUser securityUser = getSecurityUserById(secutityUserUuid);

    List<AuthorityGroup> authorityGroupsToAdd = authorityGroupIdsToAdd.stream()
        .map(uuid -> AuthorityGroup.builder()
            .id(uuid)
            .build())
        .toList();
    securityUser.getAuthorityGroups().addAll(authorityGroupsToAdd);

    securityUserRepository.save(securityUser);
  }

  @Override
  public void removeAuthorityGroupsFromUser(UUID secutityUserUuid, Set<UUID> authorityGroupIdsToRemove) {
    SecurityUser securityUser = getSecurityUserById(secutityUserUuid);

    securityUser.getAuthorityGroups()
        .removeIf(authority -> authorityGroupIdsToRemove.contains(authority.getId()));

    securityUserRepository.save(securityUser);
  }
}
