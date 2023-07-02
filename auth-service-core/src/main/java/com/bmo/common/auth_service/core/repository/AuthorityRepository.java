package com.bmo.common.auth_service.core.repository;

import com.bmo.common.auth_service.core.dbmodel.Authority;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorityRepository extends JpaRepository<Authority, UUID> {

  @Query(value = """
      with athority_ids_from_groups as (
          select a.id
          from authority a
                   left join authority_group_authorities aga
                             on a.id = aga.authorities_id
                   left join security_user_authority_groups suag
                             on aga.authority_group_id = suag.authority_groups_id
          where suag.security_user_id = :securityUserId),
          user_athority_ids as (
              select a.id
              from authority a
              inner join security_user_authorities sua on a.id = sua.authorities_id
              where sua.security_user_id = :securityUserId
          )
      select a.authority
      from authority a
      where a.id in (select * from athority_ids_from_groups) or
            a.id in (select * from user_athority_ids);
      """,
      nativeQuery = true)
  Set<String> findAllAuthoritiesBySecurityUserId(@Param("securityUserId") UUID securityUserId);
}