package com.bmo.common.authservice.dbmodel;


import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "authority_group")
public class AuthorityGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @Column(name = "description", nullable = false, unique = true)
  private String description;

  @ManyToMany(mappedBy = "authorityGroups")
  @Builder.Default
  private Set<SecurityUser> securityUsers = new LinkedHashSet<>();


  @ManyToMany
  @JoinTable(name = "authority_group_authorities",
      joinColumns = @JoinColumn(name = "authority_group_id"),
      inverseJoinColumns = @JoinColumn(name = "authorities_id"))
  @Builder.Default
  private Set<Authority> authorities = new LinkedHashSet<>();

}
