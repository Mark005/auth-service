package com.bmo.common.auth_service.core.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageableMapper {

  public static Pageable map(PageRequestDto pageRequest) {
    return PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize());
  }
}
