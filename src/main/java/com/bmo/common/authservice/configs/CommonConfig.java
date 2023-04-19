package com.bmo.common.authservice.configs;

import com.bmo.common.authservice.model.oauth2.ProviderType;
import com.bmo.common.authservice.service.provider.UserHandler;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

  @Bean
  public <T> Map<ProviderType, UserHandler<T>> providerTypeToUserHandler(List<UserHandler<T>> userHandlers) {
    return userHandlers.stream()
        .collect(Collectors.toMap(
            UserHandler::getProviderType,
            Function.identity()));
  }

}
