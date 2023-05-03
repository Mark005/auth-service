package com.bmo.common.auth_service.client;

import com.bmo.common.auth_service.client.config.properties.AuthServiceProperties;
import com.bmo.common.auth_service.model.SecurityUserDto;
import com.bmo.common.auth_service.model.TokenBody;
import com.bmo.common.auth_service.model.ValidateTokenRequestBody;
import java.util.UUID;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class AuthServiceReactiveClientImpl implements AuthServiceReactiveClient{

  private final WebClient webClient;
  private final AuthServiceProperties.Paths paths;

  public AuthServiceReactiveClientImpl(WebClient webClient, AuthServiceProperties.Paths paths) {
    this.webClient = webClient;
    this.paths = paths;
  }

  @Override
  public Mono<TokenBody> validate(ValidateTokenRequestBody validateTokenRequestBody) {
    return webClient.post()
        .uri(paths.getValidate())
        .bodyValue(validateTokenRequestBody)
        .retrieve()
        .bodyToMono(TokenBody.class);
  }

  @Override
  public Mono<SecurityUserDto> getSecurityUserInfo(UUID secutityUserUuid) {
    return webClient.get()
        .uri(builder ->
            builder.path(paths.getGetSecurityUserInfo())
                .queryParam("id", secutityUserUuid)
                .build()
        )
        .retrieve()
        .bodyToMono(SecurityUserDto.class);
  }
}
