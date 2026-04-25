package org.acs.idp.databaseservice.controller;

import org.acs.idp.databaseservice.model.dto.TaskDto;
import org.acs.idp.databaseservice.model.request.AddTaskRequest;
import org.acs.idp.databaseservice.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> findById(@PathVariable UUID id) {
        TaskDto task = taskService.findById(id);
        if (task == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(task);
    }

    @PostMapping("/quests/{questId}/tasks")
    public ResponseEntity<TaskDto> addToQuest(@PathVariable UUID questId,
                                              @RequestBody AddTaskRequest request) {
        return ResponseEntity.ok(taskService.addToQuest(questId, request));
    }

    @PatchMapping("/tasks/{id}/complete")
    public ResponseEntity<TaskDto> complete(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.complete(id));
    }
}
