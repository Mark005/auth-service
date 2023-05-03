package com.bmo.common.auth_service.core.repository;

import com.bmo.common.auth_service.core.dbmodel.SecurityUser;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityUserRepository extends JpaRepository<SecurityUser, UUID> {

}