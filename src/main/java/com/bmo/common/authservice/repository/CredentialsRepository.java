package com.bmo.common.authservice.repository;

import com.bmo.common.authservice.dbmodel.Credentials;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialsRepository extends JpaRepository<Credentials, UUID> {

  Optional<Credentials> findByLogin(String login);
}