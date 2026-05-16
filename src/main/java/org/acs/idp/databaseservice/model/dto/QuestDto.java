package org.acs.idp.databaseservice.model.dto;

import org.acs.idp.databaseservice.model.entity.enums.QuestStatus;

import java.time.Instant;
import java.util.List;

public record QuestDto(String id,
                       String ownerEmail,
                       String helperEmail,
                       String title,
                       String description,
                       QuestStatus status,
                       Instant createdAt,
                       List<TaskDto> tasks) {
}
