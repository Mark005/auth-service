package com.bmo.common.auth_service.core.repository;

import com.bmo.common.auth_service.core.dbmodel.AuthorityGroup;
import com.bmo.common.auth_service.core.dbmodel.GroupTag;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityGroupRepository extends JpaRepository<AuthorityGroup, UUID> {

  AuthorityGroup findByGroupTag(GroupTag groupTag);
}