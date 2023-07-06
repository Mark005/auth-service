package com.bmo.common.auth_service.core.service;

import com.bmo.common.auth_service.core.dbmodel.AuthorityGroup;
import com.bmo.common.auth_service.core.utils.PageRequestDto;
import org.springframework.data.domain.Page;

public interface AuthorityGroupService {

  Page<AuthorityGroup> findAllAuthorityGroups(PageRequestDto pageRequestDto);
}
