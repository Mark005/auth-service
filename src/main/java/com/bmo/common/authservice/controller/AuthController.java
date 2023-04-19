package com.bmo.common.authservice.controller;

import java.net.URISyntaxException;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

//  private final GithubClient githubClient;
//
//  @GetMapping("/oauth-github")
//  public ResponseEntity<Void> githubAuth() throws URISyntaxException {
//
//    URIBuilder builder = new URIBuilder();
//    builder.setScheme("https");
//    builder.setHost("github.com");
//    builder.setPath("/login/oauth/authorize");
//    builder.addParameter("response_type", "code");
//    builder.addParameter("client_id", "3be5dba121b074dcd72d");
//    builder.addParameter("scope", "read:user");
//    builder.addParameter("state", UUID.randomUUID().toString());
//    builder.addParameter("redirect_uri", "http://localhost:8080/login/oauth2/code/github");
//
//    String query = builder.build().toString();
//    return ResponseEntity.status(HttpStatus.FOUND)
//        .header(HttpHeaders.LOCATION, query)
//        .build();
//  }
//
//  @GetMapping("/login/oauth2/code/github")
//  public ResponseEntity<Object> getCode(@RequestParam String state, @RequestParam String code) {
//
//    AccessTokenResponse accessToken = githubClient.getAccessToken(AccessTokenRequest.builder()
//        .clientId("3be5dba121b074dcd72d")
//        .clientSecret("411e16d70ebd97e2fe47553de7906a69a0cf8243")
//        .code(code)
//        .build());
//
//
//    return ResponseEntity.ok(Map.of("token", accessToken));
//  }
}
