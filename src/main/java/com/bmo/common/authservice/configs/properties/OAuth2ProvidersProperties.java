package com.bmo.common.authservice.configs.properties;


import com.bmo.common.authservice.model.oauth2.Provider;
import java.util.ArrayList;
import java.util.List;
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

  private List<ProviderSettings> providers = new ArrayList<>();

  public Set<String> getProvidersAuthRequestUris() {
    return providers
        .stream()
        .map(ProviderSettings::getAuthorizationRequestUri)
        .collect(Collectors.toSet());
  }

  public Set<String> getProvidersRedirectUrls() {
    return providers
        .stream()
        .map(ProviderSettings::getRedirectUrl)
        .collect(Collectors.toSet());
  }

  public ProviderSettings getProviderByAuthRequestUri(String authRequestUri) {
    return providers
        .stream()
        .filter(providerSettings -> providerSettings.getAuthorizationRequestUri().equals(authRequestUri))
        .findFirst()
        .orElse(null);
  }

  public ProviderSettings getProviderByRedirectUrl(String redirectUrl) {
    return providers
        .stream()
        .filter(providerSettings -> providerSettings.getRedirectUrl().equals(redirectUrl))
        .findFirst()
        .orElse(null);
  }

  @Data
  public static class ProviderSettings {

    @NotNull
    private Provider provider;

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
