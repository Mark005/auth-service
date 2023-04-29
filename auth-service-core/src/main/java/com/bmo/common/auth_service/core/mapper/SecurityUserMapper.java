package com.bmo.common.auth_service.core.mapper;

import com.bmo.common.auth_service.core.dbmodel.SecurityUser;
import com.bmo.common.auth_service.core.configs.MapStructCommonConfig;
import com.bmo.common.auth_service.model.SecurityUserDto;
import org.mapstruct.Mapper;

@Mapper(config = MapStructCommonConfig.class)
public interface SecurityUserMapper {

  SecurityUserDto map(SecurityUser source);

}
