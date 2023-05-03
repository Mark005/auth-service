package com.bmo.common.auth_service.core.filter;

import com.bmo.common.auth_service.core.configs.properties.OAuth2ProvidersProperties;
import com.bmo.common.auth_service.core.configs.properties.OAuth2ProvidersProperties.ProviderSettings;
import com.bmo.common.auth_service.core.model.oauth2.AccessTokenRequestBody;
import com.bmo.common.auth_service.core.model.oauth2.AccessTokenResponseBody;
import com.bmo.common.auth_service.core.model.oauth2.Provider;
import com.bmo.common.auth_service.core.service.provider.UserHandler;
import com.bmo.common.auth_service.model.AuthToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Map;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2CodeHandlerFiler extends OncePerRequestFilter {

  private final ObjectMapper objectMapper;
  private final RestTemplate restTemplate = new RestTemplate();
  private final OAuth2ProvidersProperties oAuthProperties;

  private final Map<Provider, UserHandler<?>> userHandlersMap;

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    Set<String> redirectUris = oAuthProperties.getProvidersRedirectUris();

    String requestUri = request.getRequestURI().toString();
    return !redirectUris.contains(requestUri);
  }

  @SneakyThrows
  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) {

    String redirectUri = request.getRequestURI().toString();
    ProviderSettings providerSettings = oAuthProperties.getProviderByRedirectUri(redirectUri);
    Assert.notNull(providerSettings, "Provider can not be null");

    String code = request.getParameter("code");
    Assert.hasText(code, "Code not found in response");

    AccessTokenResponseBody tokenResponse = getToken(providerSettings, code);

    ObjectNode userJson = getUserJson(providerSettings, tokenResponse);

    Provider provider = providerSettings.getProvider();

    UserHandler<Object> userHandler = (UserHandler<Object>) userHandlersMap.get(provider);
    Object providedUser = objectMapper.readValue(userJson.toString(), userHandler.getProvidedUserType());

    if (!userHandler.isUserExists(providedUser)) {
      userHandler.createUser(providedUser);
    }

    AuthToken authToken = userHandler.generateAuthToken(providedUser);
    response.getWriter().write(objectMapper.writeValueAsString(authToken));
  }

  private AccessTokenResponseBody getToken(ProviderSettings providerSettings, String code) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    AccessTokenRequestBody accessTokenRequestBody = AccessTokenRequestBody.builder()
        .clientId(providerSettings.getClientId())
        .clientSecret(providerSettings.getClientSecret())
        .code(code)
        .build();

    HttpEntity<AccessTokenRequestBody> request = new HttpEntity<>(accessTokenRequestBody, headers);
    ResponseEntity<AccessTokenResponseBody> response = restTemplate.postForEntity(
        providerSettings.getTokenUrl(), request, AccessTokenResponseBody.class);
    return response.getBody();
  }


  private ObjectNode getUserJson(ProviderSettings providerSettings, AccessTokenResponseBody tokenResponse) {

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(tokenResponse.getAccessToken());
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<ObjectNode> responseEntity = restTemplate.exchange(
        providerSettings.getUserInfoUrl(),
        HttpMethod.GET,
        requestEntity,
        ObjectNode.class);

    ObjectNode userJson = responseEntity.getBody();
    return userJson;
  }
}
