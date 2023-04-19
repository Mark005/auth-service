package com.bmo.common.authservice.service;

import com.bmo.common.authservice.exception.TokenNotValidException;
import com.bmo.common.authservice.model.TokenBody;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtTokenTokenProviderImpl implements JwtTokenProvider {

  private static final String TOKEN_BODY_CLAIM_NAME = "tokenBody";

  @Value("${jwt.expiration-hours}")
  private Integer expirationHours;

  @Value("${jwt.secret}")
  private String secret;

  @Override
  public String generateToken(TokenBody tokenBody) {
    String jwtToken = Jwts.builder()
        .claim(TOKEN_BODY_CLAIM_NAME, tokenBody)
        .setId(UUID.randomUUID().toString())
        .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
        .setExpiration(
            Date.from(ZonedDateTime.now()
                .plus(expirationHours, ChronoUnit.HOURS)
                .toInstant()))
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();

    return jwtToken;
  }

  @Override
  public TokenBody parseToken(String token) {
    try {
      TokenBody tokenBody = Jwts.parser()
          .setSigningKey(secret)
          .parseClaimsJws(token)
          .getBody()
          .get(TOKEN_BODY_CLAIM_NAME, TokenBody.class);
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
