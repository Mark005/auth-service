package com.bmo.common.auth_service.client;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = AuthServiceClient.class)
@ConditionalOnProperty("feign.client.config.auth-service.url")
public class AuthServiceAutoconfiguration {
}
