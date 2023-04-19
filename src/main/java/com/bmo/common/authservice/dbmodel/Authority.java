package com.bmo.common.authservice.dbmodel;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "authority")
public class Authority {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "authority", nullable = false, unique = true)
  private String authority;

  @Column(name = "description", nullable = false, unique = true)
  private String description;

  @Builder.Default
  @ManyToMany(mappedBy = "authorities")
  private Set<AuthorityGroup> authorityGroups = new LinkedHashSet<>();

  @Builder.Default
  @ManyToMany(mappedBy = "authorities")
  private Set<SecurityUser> securityUsers = new LinkedHashSet<>();


}
