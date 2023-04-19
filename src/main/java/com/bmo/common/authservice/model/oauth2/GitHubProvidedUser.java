package com.bmo.common.authservice.model.oauth2;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class GitHubProvidedUser {

  private Long id;

  private String login;

  @JsonProperty("avatar_url")
  private String avatarUrl;

  @JsonProperty("gravatar_id")
  private String gravatarId;

  private String url;

  @JsonProperty("html_url")
  private String htmlUrl;

  private String name;

  private String location;

  private String email;

  @JsonProperty("twitter_username")
  private String twitterUsername;

}
