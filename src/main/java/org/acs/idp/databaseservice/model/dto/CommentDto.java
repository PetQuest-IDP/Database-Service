package org.acs.idp.databaseservice.model.dto;

import java.time.Instant;

public record CommentDto(String id,
                         String questId,
                         String authorEmail,
                         String content,
                         Instant createdAt) {
}
