package com.bmo.common.auth_service.core.service.provider;

import com.bmo.common.auth_service.model.AuthToken;
import com.bmo.common.auth_service.core.model.oauth2.GitHubProvidedUser;
import com.bmo.common.auth_service.core.model.oauth2.Provider;
import com.bmo.common.auth_service.core.dbmodel.AuthorityGroup;
import com.bmo.common.auth_service.core.dbmodel.GitHubUser;
import com.bmo.common.auth_service.core.dbmodel.GroupTag;
import com.bmo.common.auth_service.core.dbmodel.SecurityUser;
import com.bmo.common.auth_service.model.exception.EntityNotFoundException;
import com.bmo.common.auth_service.core.mapper.GitHubUserMapper;
import com.bmo.common.auth_service.core.repository.AuthorityGroupRepository;
import com.bmo.common.auth_service.core.repository.GitHubUserRepository;
import com.bmo.common.auth_service.core.repository.SecurityUserRepository;
import com.bmo.common.auth_service.core.service.AuthService;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GitHubUserHandler implements UserHandler<GitHubProvidedUser> {

  private final AuthService authService;

  private final GitHubUserRepository gitHubUserRepository;
  private final SecurityUserRepository securityUserRepository;
  private final AuthorityGroupRepository authorityGroupRepository;

  private final GitHubUserMapper gitHubUserMapper;

  @Override
  public TypeReference<GitHubProvidedUser> getProvidedUserType() {
    return new TypeReference<GitHubProvidedUser>() {};
  }

  @Override
  public boolean isUserExists(GitHubProvidedUser providedUser) {
    return gitHubUserRepository.existsById(providedUser.getId());
  }

  @Override
  public Provider getProviderType() {
    return Provider.GITHUB;
  }

  @Override
  @Transactional
  public GitHubProvidedUser createUser(GitHubProvidedUser providedUser) {
    GitHubUser gitHubUser = gitHubUserMapper.map(providedUser);

    AuthorityGroup authorityGroup = authorityGroupRepository.findByGroupTag(GroupTag.CUSTOMER);

    SecurityUser securityUser = SecurityUser.builder()
        .name(gitHubUser.getName())
        .email(gitHubUser.getEmail())
        .authorityGroups(List.of(authorityGroup))
        .build();
    securityUserRepository.save(securityUser);

    gitHubUser.setSecurityUser(securityUser);
    gitHubUserRepository.save(gitHubUser);
    return providedUser;
  }

  @Override
  public AuthToken generateAuthToken(GitHubProvidedUser providedUser) {

    Long providedUserId = providedUser.getId();
    GitHubUser gitHubUser = gitHubUserRepository.findById(providedUserId)
        .orElseThrow(() ->
            new EntityNotFoundException("GitHubUser with id {%s} not found".formatted(providedUserId)));

    SecurityUser securityUser = gitHubUser.getSecurityUser();

    AuthToken authToken = authService.generateToken(securityUser);
    return authToken;
  }
}
