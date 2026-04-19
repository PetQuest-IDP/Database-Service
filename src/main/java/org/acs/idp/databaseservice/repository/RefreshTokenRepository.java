package org.acs.idp.databaseservice.repository;

import org.acs.idp.databaseservice.model.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, UUID> {
    Optional<RefreshTokenEntity> findByToken(String token);
    void deleteByToken(String token);
    void deleteAllByEmail(String email);
}
