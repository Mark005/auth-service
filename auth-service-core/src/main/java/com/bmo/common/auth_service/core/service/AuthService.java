package com.bmo.common.auth_service.core.service;

import com.bmo.common.auth_service.core.dbmodel.SecurityUser;
import com.bmo.common.auth_service.model.AuthToken;
import com.bmo.common.auth_service.model.LoginRequestBody;
import com.bmo.common.auth_service.model.RegisterRequestBody;
import com.bmo.common.auth_service.model.TokenBody;
import com.bmo.common.auth_service.model.ValidateTokenRequestBody;

public interface AuthService {

  AuthToken register(RegisterRequestBody registerRequestBody);

  AuthToken login(LoginRequestBody loginRequestBody);

  AuthToken generateToken(SecurityUser securityUser);

  TokenBody validate(ValidateTokenRequestBody validateTokenRequestBody);
}
