package com.bmo.common.authservice.repository;

import com.bmo.common.authservice.dbmodel.GitHubUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GitHubUserRepository extends JpaRepository<GitHubUser, Long> {

}