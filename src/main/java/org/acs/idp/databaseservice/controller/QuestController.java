package org.acs.idp.databaseservice.controller;

import org.acs.idp.databaseservice.model.dto.QuestDto;
import org.acs.idp.databaseservice.model.entity.enums.QuestStatus;
import org.acs.idp.databaseservice.model.request.SaveQuestRequest;
import org.acs.idp.databaseservice.service.QuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/quests")
public class QuestController {
    private final QuestService questService;

    public QuestController(QuestService questService) {
        this.questService = questService;
    }

    @GetMapping
    public ResponseEntity<List<QuestDto>> findAll(
            @RequestParam(required = false) QuestStatus status,
            @RequestParam(required = false) String ownerEmail,
            @RequestParam(required = false) String helperEmail) {

        if (status != null) return ResponseEntity.ok(questService.findByStatus(status));
        if (ownerEmail != null) return ResponseEntity.ok(questService.findByOwnerEmail(ownerEmail));
        if (helperEmail != null) return ResponseEntity.ok(questService.findByHelperEmail(helperEmail));
        return ResponseEntity.ok(questService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestDto> findById(@PathVariable UUID id) {
        QuestDto quest = questService.findById(id);
        if (quest == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(quest);
    }

    @PostMapping
    public ResponseEntity<QuestDto> save(@RequestBody SaveQuestRequest request) {
        return ResponseEntity.ok(questService.save(request));
    }

    @PatchMapping("/{id}/accept")
    public ResponseEntity<QuestDto> accept(@PathVariable UUID id, @RequestParam String helperEmail) {
        return ResponseEntity.ok(questService.accept(id, helperEmail));
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<QuestDto> complete(@PathVariable UUID id) {
        return ResponseEntity.ok(questService.complete(id));
    }
}
