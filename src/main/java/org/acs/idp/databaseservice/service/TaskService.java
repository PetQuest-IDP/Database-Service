package org.acs.idp.databaseservice.service;

import org.acs.idp.databaseservice.mapper.TaskMapper;
import org.acs.idp.databaseservice.model.dto.TaskDto;
import org.acs.idp.databaseservice.model.entity.QuestEntity;
import org.acs.idp.databaseservice.model.entity.TaskEntity;
import org.acs.idp.databaseservice.model.request.AddTaskRequest;
import org.acs.idp.databaseservice.repository.QuestRepository;
import org.acs.idp.databaseservice.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final QuestRepository questRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository,
                       QuestRepository questRepository,
                       TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.questRepository = questRepository;
        this.taskMapper = taskMapper;
    }

    public TaskDto findById(UUID id) {
        return taskRepository.findById(id).map(taskMapper::entityToDto).orElse(null);
    }

    public TaskDto addToQuest(UUID questId, AddTaskRequest request) {
        QuestEntity quest = questRepository.findById(questId)
                .orElseThrow(() -> new RuntimeException("Quest not found"));

        TaskEntity task = new TaskEntity();
        task.setQuest(quest);
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setCompleted(false);

        return taskMapper.entityToDto(taskRepository.save(task));
    }

    public TaskDto complete(UUID taskId) {
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setCompleted(true);
        task.setCompletedAt(Instant.now());
        return taskMapper.entityToDto(taskRepository.save(task));
    }
}
