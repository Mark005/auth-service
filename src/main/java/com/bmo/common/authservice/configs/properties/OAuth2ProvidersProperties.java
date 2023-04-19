package com.bmo.common.authservice.configs.properties;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
@Validated
@ConfigurationProperties(prefix = "oauth2.client")
public class OAuth2ProvidersProperties {

  private Map<String, Provider> providers = new HashMap<>();

  public Set<String> getProviderIds() {
    return providers.keySet();
  }

  public Set<String> getProvidersAuthRequestUris() {
    return providers
        .values()
        .stream()
        .map(Provider::getAuthorizationRequestUri)
        .collect(Collectors.toSet());
  }

  public Set<String> getProvidersRedirectUrls() {
    return providers
        .values()
        .stream()
        .map(Provider::getRedirectUrl)
        .collect(Collectors.toSet());
  }

  public Provider getProviderByAuthRequestUri(String authRequestUri) {
    return providers
        .values()
        .stream()
        .filter(provider -> provider.getAuthorizationRequestUri().equals(authRequestUri))
        .findFirst()
        .orElse(null);
  }

  public Provider getProviderByRedirectUrl(String redirectUrl) {
    return providers
        .values()
        .stream()
        .filter(provider -> provider.getRedirectUrl().equals(redirectUrl))
        .findFirst()
        .orElse(null);
  }

  public Provider getProviderById(String providerId) {
    Provider provider = providers.get(providerId);
    if (provider == null) {
      throw new IllegalArgumentException("ProviderType should not be null");
    }
    return provider;
  }

  @Data
  public static class Provider {

    @NotNull
    private String clientId;

    @NotBlank
    private String clientSecret;

    @NotBlank
    private String authorizationRequestUri;

    @NotBlank
    private String authorizationUrl;

    @NotBlank
    private String redirectUrl;

    @NotBlank
    private String tokenUrl;

    @NotBlank
    private String userInfoUrl;

    @NotBlank
    private String scope;
  }

}
