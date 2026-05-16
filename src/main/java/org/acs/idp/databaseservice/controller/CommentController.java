package org.acs.idp.databaseservice.controller;

import org.acs.idp.databaseservice.model.dto.CommentDto;
import org.acs.idp.databaseservice.model.request.SaveCommentRequest;
import org.acs.idp.databaseservice.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/quests/{questId}/comments")
    public ResponseEntity<List<CommentDto>> findByQuestId(@PathVariable UUID questId) {
        return ResponseEntity.ok(commentService.findByQuestId(questId));
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentDto> save(@RequestBody SaveCommentRequest request) {
        return ResponseEntity.ok(commentService.save(request));
    }
}
