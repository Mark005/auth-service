package com.bmo.common.authservice.service;

import com.bmo.common.authservice.dbmodel.SecurityUser;
import java.util.UUID;

public interface SecurityUserService {

  SecurityUser getSecurityUserById(UUID securityUserId);

}
