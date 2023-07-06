package com.bmo.common.auth_service.core.utils;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PageRequestDto {

  @NotNull
  private Integer pageSize;

  @NotNull
  private Integer pageNumber;
}
