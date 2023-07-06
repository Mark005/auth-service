package com.bmo.common.auth_service.core.service;

import com.bmo.common.auth_service.core.dbmodel.Authority;
import com.bmo.common.auth_service.core.repository.AuthorityRepository;
import com.bmo.common.auth_service.core.utils.PageRequestDto;
import com.bmo.common.auth_service.core.utils.PageableMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

  private final AuthorityRepository authorityRepository;

  @Override
  public Page<Authority> findAllAuthorities(PageRequestDto pageRequestDto) {
    return authorityRepository.findAll(PageableMapper.map(pageRequestDto));
  }
}
