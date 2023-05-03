package com.bmo.common.auth_service.core.mapper;

import com.bmo.common.auth_service.core.dbmodel.SecurityUser;
import com.bmo.common.auth_service.core.configs.MapStructCommonConfig;
import com.bmo.common.auth_service.model.SecurityUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructCommonConfig.class)
public interface SecurityUserMapper {

  @Mapping(target = "securityUserId", source = "id")
  SecurityUserDto map(SecurityUser source);

}
