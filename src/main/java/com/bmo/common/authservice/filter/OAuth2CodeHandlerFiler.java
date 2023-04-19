package com.bmo.common.authservice.filter;

import com.bmo.common.authservice.model.AuthToken;
import com.bmo.common.authservice.model.oauth2.ProviderType;
import com.bmo.common.authservice.service.provider.UserHandler;
import com.bmo.common.authservice.model.oauth2.AccessTokenRequest;
import com.bmo.common.authservice.model.oauth2.AccessTokenResponse;
import com.bmo.common.authservice.configs.properties.OAuth2ProvidersProperties;
import com.bmo.common.authservice.configs.properties.OAuth2ProvidersProperties.Provider;
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

  private final Map<ProviderType, UserHandler<?>> userHandlersMap;

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    Set<String> redirectUrls = oAuthProperties.getProvidersRedirectUrls();

    String requestUrl = request.getRequestURL().toString();
    return !redirectUrls.contains(requestUrl);
  }

  @SneakyThrows
  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) {

    String redirectUrl = request.getRequestURL().toString();
    Provider provider = oAuthProperties.getProviderByRedirectUrl(redirectUrl);
    Assert.notNull(provider, "ProviderType can not be null");

    String code = request.getParameter("code");
    Assert.hasText(code, "Code not found in response");

    AccessTokenResponse tokenResponse = getToken(provider, code);

    ObjectNode userJson = getUserJson(provider, tokenResponse);

    ProviderType github = ProviderType.GITHUB;

    UserHandler<Object> userHandler = (UserHandler<Object>) userHandlersMap.get(github);
    Object providedUser = objectMapper.readValue(userJson.toString(), userHandler.getProvidedUserType());

    if (!userHandler.isUserExists(providedUser)) {
      userHandler.createUser(providedUser);
    }

    AuthToken authToken = userHandler.generateAuthToken(providedUser);
    response.getWriter().write(objectMapper.writeValueAsString(authToken));
  }

  private AccessTokenResponse getToken(Provider provider, String code) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    AccessTokenRequest accessTokenRequest = AccessTokenRequest.builder()
        .clientId(provider.getClientId())
        .clientSecret(provider.getClientSecret())
        .code(code)
        .build();

    HttpEntity<AccessTokenRequest> request = new HttpEntity<>(accessTokenRequest, headers);
    ResponseEntity<AccessTokenResponse> response = restTemplate.postForEntity(
        provider.getTokenUrl(), request, AccessTokenResponse.class);
    return response.getBody();
  }


  private ObjectNode getUserJson(Provider provider, AccessTokenResponse tokenResponse) {

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(tokenResponse.getAccessToken());
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<ObjectNode> responseEntity = restTemplate.exchange(
        provider.getUserInfoUrl(),
        HttpMethod.GET,
        requestEntity,
        ObjectNode.class);

    ObjectNode userJson = responseEntity.getBody();
    return userJson;
  }
}
