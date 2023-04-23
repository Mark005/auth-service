package com.bmo.common.authservice.repository;

import com.bmo.common.authservice.dbmodel.Authority;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorityRepository extends JpaRepository<Authority, UUID> {

  @Query(value = """
      select a.authority
      from authority a
               left join authority_group_authorities aga
                         on a.id = aga.authorities_id
               left join security_user_authority_groups suag
                         on aga.authority_group_id = suag.authority_groups_id
      where suag.security_user_id = :securityUserId
      """,
      nativeQuery = true)
  Set<String> findAllAuthoritiesBySecurityUserId(@Param("securityUserId") UUID securityUserId);
}