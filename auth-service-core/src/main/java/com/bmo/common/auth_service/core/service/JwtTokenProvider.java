package com.bmo.common.auth_service.core.service;


import com.bmo.common.auth_service.model.TokenBody;
import com.bmo.common.auth_service.model.AuthToken;

public interface JwtTokenProvider {

  AuthToken generateToken(TokenBody tokenBody);
  TokenBody parseToken(String token);

}
