package org.acs.idp.databaseservice.repository;

import org.acs.idp.databaseservice.model.entity.QuestEntity;
import org.acs.idp.databaseservice.model.entity.enums.QuestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuestRepository extends JpaRepository<QuestEntity, UUID> {
    List<QuestEntity> findByStatus(QuestStatus status);

    List<QuestEntity> findByOwnerEmail(String ownerEmail);

    List<QuestEntity> findByHelperEmail(String helperEmail);
}
