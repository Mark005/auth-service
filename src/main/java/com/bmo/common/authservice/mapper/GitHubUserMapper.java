package com.bmo.common.authservice.mapper;

import com.bmo.common.authservice.configs.MapStructCommonConfig;
import com.bmo.common.authservice.dbmodel.GitHubUser;
import com.bmo.common.authservice.model.oauth2.GitHubProvidedUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructCommonConfig.class)
public interface GitHubUserMapper {

  @Mapping(target = "securityUser", ignore = true)
  GitHubUser map(GitHubProvidedUser gitHubProvidedUser);

}
