package com.bmo.common.auth_service.core.mapper;

import com.bmo.common.auth_service.core.configs.MapStructCommonConfig;
import com.bmo.common.auth_service.core.dbmodel.Authority;
import com.bmo.common.auth_service.core.dbmodel.GitHubUser;
import com.bmo.common.auth_service.core.model.oauth2.GitHubProvidedUser;
import com.bmo.common.auth_service.model.AuthorityDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructCommonConfig.class)
public interface AuthorityMapper {

  AuthorityDto toResponseDto(Authority authority);

}
