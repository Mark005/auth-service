package com.bmo.common.authservice.service.provider;

import com.bmo.common.authservice.model.AuthToken;
import com.bmo.common.authservice.model.TokenBody;
import com.bmo.common.authservice.model.oauth2.GitHubProvidedUser;
import com.bmo.common.authservice.model.oauth2.ProviderType;
import com.bmo.common.authservice.service.JwtTokenProvider;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GitHubUserHandler implements UserHandler<GitHubProvidedUser> {
  private final JwtTokenProvider tokenProvider;

  @Override
  public TypeReference<GitHubProvidedUser> getProvidedUserType() {
    return new TypeReference<GitHubProvidedUser>() {};
  }

  @Override
  public boolean isUserExists(GitHubProvidedUser value) {
    return false;
  }

  @Override
  public ProviderType getProviderType() {
    return ProviderType.GITHUB;
  }

  @Override
  public GitHubProvidedUser createUser(GitHubProvidedUser providedUser) {

    System.out.println(providedUser);
    return providedUser;
  }

  @Override
  public AuthToken generateAuthToken(GitHubProvidedUser providedUser) {

    TokenBody tokenBody = TokenBody.builder()
        .userId(UUID.randomUUID())
        .build();
    String token = tokenProvider.generateToken(tokenBody);

    //AuthToken authToken = AuthToken.builder()
    //    .token(token)
    //    .expiresAt()
    //    .build();
    return AuthToken.builder().token("test").build();
  }
}
