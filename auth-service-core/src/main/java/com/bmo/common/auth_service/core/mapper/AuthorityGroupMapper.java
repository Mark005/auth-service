package com.bmo.common.auth_service.core.mapper;

import com.bmo.common.auth_service.core.configs.MapStructCommonConfig;
import com.bmo.common.auth_service.core.dbmodel.AuthorityGroup;
import com.bmo.common.auth_service.model.Authority;
import com.bmo.common.auth_service.model.AuthorityDto;
import com.bmo.common.auth_service.model.AuthorityGroupDto;
import org.mapstruct.Mapper;

@Mapper(config = MapStructCommonConfig.class)
public interface AuthorityGroupMapper {

  AuthorityGroupDto toResponseDto(AuthorityGroup authorityGroup);
}
