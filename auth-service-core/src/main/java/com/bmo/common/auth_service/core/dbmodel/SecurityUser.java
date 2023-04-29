package com.bmo.common.auth_service.core.dbmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "security_user")
public class SecurityUser {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private UUID id;

  private String name;

  private String surname;

  private String email;

  @ManyToMany
  @JoinTable(name = "security_user_authority_groups",
      joinColumns = @JoinColumn(name = "security_user_id"),
      inverseJoinColumns = @JoinColumn(name = "authority_groups_id"))
  @Builder.Default
  private List<AuthorityGroup> authorityGroups = new ArrayList<>();

  @ManyToMany
  @JoinTable(name = "security_user_authorities",
      joinColumns = @JoinColumn(name = "security_user_id"),
      inverseJoinColumns = @JoinColumn(name = "authorities_id"))
  @Builder.Default
  private List<Authority> authorities = new ArrayList<>();

  @OneToOne(mappedBy = "securityUser", orphanRemoval = true)
  private GitHubUser gitHubUser;

}
