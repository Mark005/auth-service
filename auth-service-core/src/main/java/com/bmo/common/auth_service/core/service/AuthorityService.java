package com.bmo.common.auth_service.core.service;

import com.bmo.common.auth_service.core.dbmodel.Authority;
import com.bmo.common.auth_service.core.utils.PageRequestDto;
import org.springframework.data.domain.Page;

public interface AuthorityService {

  Page<Authority> findAllAuthorities(PageRequestDto pageRequestDto);
}
