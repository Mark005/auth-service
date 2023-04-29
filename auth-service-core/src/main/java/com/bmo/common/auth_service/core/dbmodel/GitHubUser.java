package com.bmo.common.auth_service.core.dbmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "git_hub_user")
public class GitHubUser {

  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  private String login;

  @Column(name = "avatar_url")
  private String avatarUrl;

  @Column(name = "html_url")
  private String htmlUrl;

  private String name;

  private String location;

  private String email;

  @Column(name = "twitter_username")
  private String twitterUsername;

  @Fetch(FetchMode.JOIN)
  @OneToOne(optional = false, orphanRemoval = true)
  @JoinColumn(name = "security_user_id", nullable = false, unique = true)
  private SecurityUser securityUser;

}
