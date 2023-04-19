package com.bmo.common.authservice.filter;

import com.bmo.common.authservice.configs.properties.OAuth2ProvidersProperties;
import com.bmo.common.authservice.configs.properties.OAuth2ProvidersProperties.ProviderSettings;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.UUID;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class OAuth2LoginRedirectFiler extends OncePerRequestFilter {

  private final OAuth2ProvidersProperties oAuthProperties;

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    Set<String> loginUris = oAuthProperties.getProvidersAuthRequestUris();

    String requestUrl = request.getRequestURI();
    return !loginUris.contains(requestUrl);
  }

  @SneakyThrows
  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) {

    String requestUrl = request.getRequestURI();
    ProviderSettings providerSettings = oAuthProperties.getProviderByAuthRequestUri(requestUrl);
    Assert.notNull(providerSettings, "Provider can not be null");

    URIBuilder builder = new URIBuilder(providerSettings.getAuthorizationUrl());
    builder.addParameter("response_type", "code");
    builder.addParameter("client_id", providerSettings.getClientId());
    builder.addParameter("scope", providerSettings.getScope());
    builder.addParameter("state", UUID.randomUUID().toString());
    builder.addParameter("redirect_uri", providerSettings.getRedirectUrl());

    String query = builder.build().toString();
    String decodedUrl = URLDecoder.decode(query, StandardCharsets.UTF_8);
    response.sendRedirect(decodedUrl);
  }
}
