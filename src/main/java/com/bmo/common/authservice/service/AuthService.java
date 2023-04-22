package com.bmo.common.authservice.service;

import com.bmo.common.authservice.dbmodel.SecurityUser;
import com.bmo.common.authservice.model.AuthToken;
import com.bmo.common.authservice.model.LoginRequestBody;
import com.bmo.common.authservice.model.RegisterRequestBody;
import com.bmo.common.authservice.model.TokenBody;
import com.bmo.common.authservice.model.ValidateTokenRequestBody;

public interface AuthService {

  AuthToken register(RegisterRequestBody registerRequestBody);

  AuthToken login(LoginRequestBody loginRequestBody);

  AuthToken generateToken(SecurityUser securityUser);

  TokenBody validate(ValidateTokenRequestBody validateTokenRequestBody);
}
