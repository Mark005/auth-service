package com.bmo.common.auth_service.core.mapper;

import com.bmo.common.auth_service.core.dbmodel.GitHubUser;
import com.bmo.common.auth_service.core.model.oauth2.GitHubProvidedUser;
import com.bmo.common.auth_service.core.configs.MapStructCommonConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructCommonConfig.class)
public interface GitHubUserMapper {

  @Mapping(target = "securityUser", ignore = true)
  GitHubUser map(GitHubProvidedUser gitHubProvidedUser);

}
