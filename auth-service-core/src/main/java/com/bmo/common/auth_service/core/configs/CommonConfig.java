package com.bmo.common.auth_service.core.configs;

import com.bmo.common.auth_service.core.model.oauth2.Provider;
import com.bmo.common.auth_service.core.service.provider.UserHandler;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class CommonConfig {

  @Bean
  public <T> Map<Provider, UserHandler<T>> providerTypeToUserHandler(List<UserHandler<T>> userHandlers) {
    return userHandlers.stream()
        .collect(Collectors.toMap(
            UserHandler::getProviderType,
            Function.identity()));
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
