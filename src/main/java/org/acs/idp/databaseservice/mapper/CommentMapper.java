package org.acs.idp.databaseservice.mapper;

import org.acs.idp.databaseservice.model.dto.CommentDto;
import org.acs.idp.databaseservice.model.entity.CommentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public CommentDto entityToDto(CommentEntity comment) {
        return new CommentDto(
                comment.getId().toString(),
                comment.getQuestId().toString(),
                comment.getAuthorEmail(),
                comment.getContent(),
                comment.getCreatedAt()
        );
    }
}
