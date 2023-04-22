package com.bmo.common.authservice.configs.properties;


import java.time.Duration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
@Validated
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

  private Duration validDuration;
  private String secret;
}
