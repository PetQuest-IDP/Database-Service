package org.acs.idp.databaseservice.service;

import org.acs.idp.databaseservice.mapper.CommentMapper;
import org.acs.idp.databaseservice.model.dto.CommentDto;
import org.acs.idp.databaseservice.model.entity.CommentEntity;
import org.acs.idp.databaseservice.model.request.SaveCommentRequest;
import org.acs.idp.databaseservice.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public List<CommentDto> findByQuestId(UUID questId) {
        return commentRepository.findByQuestIdOrderByCreatedAtAsc(questId).stream()
                .map(commentMapper::entityToDto)
                .toList();
    }

    public CommentDto save(SaveCommentRequest request) {
        CommentEntity comment = new CommentEntity();
        comment.setQuestId(request.questId());
        comment.setAuthorEmail(request.authorEmail());
        comment.setContent(request.content());
        return commentMapper.entityToDto(commentRepository.save(comment));
    }
}
