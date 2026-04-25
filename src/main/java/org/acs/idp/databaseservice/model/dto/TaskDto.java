package org.acs.idp.databaseservice.model.dto;

import java.time.Instant;

public record TaskDto(String id,
                      String questId,
                      String title,
                      String description,
                      boolean completed,
                      Instant completedAt) {
}