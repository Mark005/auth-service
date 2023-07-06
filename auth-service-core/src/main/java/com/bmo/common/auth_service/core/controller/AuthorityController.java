package com.bmo.common.auth_service.core.controller;

import com.bmo.common.auth_service.core.dbmodel.Authority;
import com.bmo.common.auth_service.core.dbmodel.AuthorityGroup;
import com.bmo.common.auth_service.core.mapper.AuthorityGroupMapper;
import com.bmo.common.auth_service.core.mapper.AuthorityMapper;
import com.bmo.common.auth_service.core.service.AuthorityGroupService;
import com.bmo.common.auth_service.core.service.AuthorityService;
import com.bmo.common.auth_service.core.utils.PageRequestDto;
import com.bmo.common.auth_service.model.AuthorityDto;
import com.bmo.common.auth_service.model.AuthorityGroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthorityController {

  private final AuthorityService authorityService;
  private final AuthorityGroupService authorityGroupService;

  private final AuthorityMapper authorityMapper;
  private final AuthorityGroupMapper authorityGroupMapper;

  @GetMapping("/authorities")
  public ResponseEntity<Page<AuthorityDto>> findAllAuthorities(
      @Validated PageRequestDto pageRequestDto) {
    Page<Authority> authorities = authorityService.findAllAuthorities(pageRequestDto);
    Page<AuthorityDto> response = authorities.map(authorityMapper::toResponseDto);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/authority-groups")
  public ResponseEntity<Page<AuthorityGroupDto>> findAllAuthorityGroups(
      @Validated PageRequestDto pageRequestDto) {
    Page<AuthorityGroup> authorityGroups = authorityGroupService.findAllAuthorityGroups(pageRequestDto);
    Page<AuthorityGroupDto> response = authorityGroups.map(authorityGroupMapper::toResponseDto);
    return ResponseEntity.ok(response);
  }
}
