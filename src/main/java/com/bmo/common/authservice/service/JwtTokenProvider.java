package com.bmo.common.authservice.service;

import com.bmo.common.authservice.model.AuthToken;
import com.bmo.common.authservice.model.TokenBody;

public interface JwtTokenProvider {

  AuthToken generateToken(TokenBody tokenBody);
  TokenBody parseToken(String token);

}
