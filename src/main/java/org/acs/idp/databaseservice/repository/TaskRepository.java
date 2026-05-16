package org.acs.idp.databaseservice.repository;

import org.acs.idp.databaseservice.model.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {
}
