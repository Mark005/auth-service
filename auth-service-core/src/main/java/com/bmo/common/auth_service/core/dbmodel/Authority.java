package com.bmo.common.auth_service.core.dbmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
import org.hibernate.Hibernate;

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
  private List<AuthorityGroup> authorityGroups = new ArrayList<>();

  @Builder.Default
  @ManyToMany(mappedBy = "authorities")
  private List<SecurityUser> securityUsers = new ArrayList<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Authority authority = (Authority) o;
    return getId() != null && Objects.equals(getId(), authority.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
