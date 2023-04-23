package com.bmo.common.authservice.repository;

import com.bmo.common.authservice.dbmodel.AuthorityGroup;
import com.bmo.common.authservice.dbmodel.GroupTag;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityGroupRepository extends JpaRepository<AuthorityGroup, UUID> {

  AuthorityGroup findByGroupTag(GroupTag groupTag);
}