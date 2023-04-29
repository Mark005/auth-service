package com.bmo.common.auth_service.core.service;

import com.bmo.common.auth_service.core.dbmodel.SecurityUser;
import java.util.UUID;

public interface SecurityUserService {

  SecurityUser getSecurityUserById(UUID securityUserId);

}
