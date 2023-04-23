package com.bmo.common.authservice.repository;

import com.bmo.common.authservice.dbmodel.SecurityUser;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityUserRepository extends JpaRepository<SecurityUser, UUID> {

}