package org.acs.idp.databaseservice.model.request;

import java.util.UUID;

public record SaveCommentRequest(UUID questId,
                                 String authorEmail,
                                 String content) {
}
