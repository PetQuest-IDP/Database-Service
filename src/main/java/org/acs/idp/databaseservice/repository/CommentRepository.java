package org.acs.idp.databaseservice.repository;

import org.acs.idp.databaseservice.model.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {
    List<CommentEntity> findByQuestIdOrderByCreatedAtAsc(UUID questId);
}
