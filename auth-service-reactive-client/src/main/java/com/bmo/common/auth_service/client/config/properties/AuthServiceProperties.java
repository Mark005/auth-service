package com.bmo.common.auth_service.client.config.properties;

import java.time.Duration;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "reactive-clients.auth-service-reactive-client")
public class AuthServiceProperties {

  @NotBlank
  private String baseUrl;

  private boolean httpLoggingEnabled = true;

  private Duration connectionTimeout = Duration.ofSeconds(2);

  private Duration readTimeout = Duration.ofSeconds(30);

  private Duration maxIdleTime = Duration.ofSeconds(3);

  private int maxInMemorySizeBytes = -1;

  @NestedConfigurationProperty
  private Paths paths = new Paths();

  @Data
  public static class Paths {

    @NotBlank
    private String validate = "/jwt/validate";

    @NotBlank
    private String getSecurityUserInfo = "/users/{id}";
  }
}
