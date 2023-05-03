package com.bmo.common.auth_service.client;

import com.bmo.common.auth_service.model.SecurityUserDto;
import com.bmo.common.auth_service.model.TokenBody;
import com.bmo.common.auth_service.model.ValidateTokenRequestBody;
import java.util.UUID;
import reactor.core.publisher.Mono;

public interface AuthServiceReactiveClient {

  Mono<TokenBody> validate(ValidateTokenRequestBody validateTokenRequestBody);
  Mono<SecurityUserDto> getSecurityUserInfo(UUID secutityUserUuid);
}
