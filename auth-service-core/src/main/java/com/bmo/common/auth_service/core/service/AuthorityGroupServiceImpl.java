package com.bmo.common.auth_service.core.service;

import com.bmo.common.auth_service.core.dbmodel.AuthorityGroup;
import com.bmo.common.auth_service.core.repository.AuthorityGroupRepository;
import com.bmo.common.auth_service.core.utils.PageRequestDto;
import com.bmo.common.auth_service.core.utils.PageableMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorityGroupServiceImpl implements AuthorityGroupService {

  private final AuthorityGroupRepository authorityGroupRepository;

  @Override
  public Page<AuthorityGroup> findAllAuthorityGroups(PageRequestDto pageRequestDto) {
    return authorityGroupRepository.findAll(PageableMapper.map(pageRequestDto));
  }
}
