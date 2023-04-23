package com.bmo.common.authservice.service;

import com.bmo.common.authservice.dbmodel.AuthorityGroup;
import com.bmo.common.authservice.dbmodel.Credentials;
import com.bmo.common.authservice.dbmodel.GroupTag;
import com.bmo.common.authservice.dbmodel.SecurityUser;
import com.bmo.common.authservice.model.AuthToken;
import com.bmo.common.authservice.model.LoginRequestBody;
import com.bmo.common.authservice.model.RegisterRequestBody;
import com.bmo.common.authservice.model.TokenBody;
import com.bmo.common.authservice.model.ValidateTokenRequestBody;
import com.bmo.common.authservice.repository.AuthorityGroupRepository;
import com.bmo.common.authservice.repository.AuthorityRepository;
import com.bmo.common.authservice.repository.CredentialsRepository;
import com.bmo.common.authservice.repository.SecurityUserRepository;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final JwtTokenProvider tokenProvider;
  private final SecurityUserRepository securityUserRepository;
  private final AuthorityGroupRepository authorityGroupRepository;
  private final AuthorityRepository authorityRepository;
  private final CredentialsRepository credentialsRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public AuthToken register(RegisterRequestBody registerRequestBody) {
    AuthorityGroup authorityGroup = authorityGroupRepository.findByGroupTag(GroupTag.CUSTOMER);

    SecurityUser securityUser = SecurityUser.builder()
        .name(registerRequestBody.getName())
        .surname(registerRequestBody.getSurname())
        .email(registerRequestBody.getEmail())
        .authorityGroups(List.of(authorityGroup))
        .build();

    String encodedPass = passwordEncoder.encode(registerRequestBody.getPassword());
    securityUserRepository.save(securityUser);

    Credentials credentials = Credentials.builder()
        .login(registerRequestBody.getLogin())
        .password(encodedPass)
        .securityUser(securityUser)
        .build();

    credentialsRepository.save(credentials);
    AuthToken authToken = generateToken(securityUser);
    return authToken;
  }

  @Override
  public AuthToken login(LoginRequestBody loginRequestBody) {
    Credentials credentials = credentialsRepository.findByLogin(loginRequestBody.getLogin())
        .filter(creds -> passwordEncoder.matches(loginRequestBody.getPassword(), creds.getPassword()))
        .orElseThrow(() -> new ClassCastException("Login or password are incorrect"));
    AuthToken authToken = generateToken(credentials.getSecurityUser());
    return authToken;
  }

  public AuthToken generateToken(SecurityUser securityUser) {

    UUID securityUserId = securityUser.getId();

    Set<String> authorities = authorityRepository.findAllAuthoritiesBySecurityUserId(
        securityUserId);

    TokenBody tokenBody = TokenBody.builder()
        .userId(securityUserId)
        .authorities(authorities)
        .build();

    AuthToken authToken = tokenProvider.generateToken(tokenBody);
    return authToken;
  }

  @Override
  public TokenBody validate(ValidateTokenRequestBody validateTokenRequestBody) {
    return tokenProvider.parseToken(validateTokenRequestBody.getToken());
  }
}
