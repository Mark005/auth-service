package com.bmo.common.authservice.mapper;

import com.bmo.common.authservice.configs.MapStructCommonConfig;
import com.bmo.common.authservice.dbmodel.SecurityUser;
import com.bmo.common.authservice.model.SecurityUserDto;
import org.mapstruct.Mapper;

@Mapper(config = MapStructCommonConfig.class)
public interface SecurityUserMapper {

  SecurityUserDto map(SecurityUser source);

}
