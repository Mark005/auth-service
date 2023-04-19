package com.bmo.common.authservice.service;

import com.bmo.common.authservice.model.TokenBody;

public interface JwtTokenProvider {

  String generateToken(TokenBody tokenBody);
  TokenBody parseToken(String token);

}
