package com.bmo.common.authservice.service;

import com.bmo.common.authservice.configs.properties.JwtProperties;
import com.bmo.common.authservice.exception.TokenNotValidException;
import com.bmo.common.authservice.model.AuthToken;
import com.bmo.common.authservice.model.TokenBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenTokenProviderImpl implements JwtTokenProvider {

  private static final String TOKEN_BODY_CLAIM_NAME = "tokenBody";
  private final JwtProperties jwtProperties;
  private final ObjectMapper objectMapper;

  @Override
  public AuthToken generateToken(TokenBody tokenBody) {
    ZonedDateTime expirationTime = ZonedDateTime.now()
        .plus(jwtProperties.getValidDuration());

    String jwtToken = Jwts.builder()
        .claim(TOKEN_BODY_CLAIM_NAME, tokenBody)
        .setId(UUID.randomUUID().toString())
        .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
        .setExpiration(Date.from(expirationTime.toInstant()))
        .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
        .compact();

    AuthToken authToken = AuthToken.builder()
        .token(jwtToken)
        .expiresAt(expirationTime)
        .authorities(tokenBody.getAuthorities())
        .build();
    return authToken;
  }

  @Override
  public TokenBody parseToken(String token) {
    try {
      Object obj = Jwts.parser()
          .setSigningKey(jwtProperties.getSecret())
          .parseClaimsJws(token)
          .getBody()
          .get(TOKEN_BODY_CLAIM_NAME);

      TokenBody tokenBody = objectMapper.convertValue(obj, TokenBody.class);
      return tokenBody;
    } catch (UnsupportedJwtException |
             MalformedJwtException |
             SignatureException |
             ExpiredJwtException exception) {
      log.warn("Token not valid because of : {}", exception.getMessage());
      throw new TokenNotValidException(exception.getMessage());
    }
  }

}
