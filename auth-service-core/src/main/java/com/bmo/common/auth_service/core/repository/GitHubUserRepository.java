package com.bmo.common.auth_service.core.repository;

import com.bmo.common.auth_service.core.dbmodel.GitHubUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GitHubUserRepository extends JpaRepository<GitHubUser, Long> {

}