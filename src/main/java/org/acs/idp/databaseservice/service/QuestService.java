package org.acs.idp.databaseservice.service;

import org.acs.idp.databaseservice.mapper.QuestMapper;
import org.acs.idp.databaseservice.model.dto.QuestDto;
import org.acs.idp.databaseservice.model.entity.QuestEntity;
import org.acs.idp.databaseservice.model.entity.TaskEntity;
import org.acs.idp.databaseservice.model.entity.enums.QuestStatus;
import org.acs.idp.databaseservice.model.request.SaveQuestRequest;
import org.acs.idp.databaseservice.model.request.SaveTaskRequest;
import org.acs.idp.databaseservice.repository.QuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuestService {
    private final QuestRepository questRepository;
    private final QuestMapper questMapper;

    public QuestService(QuestRepository questRepository, QuestMapper questMapper) {
        this.questRepository = questRepository;
        this.questMapper = questMapper;
    }

    public List<QuestDto> findAll() {
        return questRepository.findAll().stream().map(questMapper::entityToDto).toList();
    }

    public List<QuestDto> findByStatus(QuestStatus status) {
        return questRepository.findByStatus(status).stream().map(questMapper::entityToDto).toList();
    }

    public List<QuestDto> findByOwnerEmail(String ownerEmail) {
        return questRepository.findByOwnerEmail(ownerEmail).stream().map(questMapper::entityToDto).toList();
    }

    public List<QuestDto> findByHelperEmail(String helperEmail) {
        return questRepository.findByHelperEmail(helperEmail).stream().map(questMapper::entityToDto).toList();
    }

    public QuestDto findById(UUID id) {
        return questRepository.findById(id).map(questMapper::entityToDto).orElse(null);
    }

    public QuestDto save(SaveQuestRequest request) {
        QuestEntity quest = new QuestEntity();
        quest.setOwnerEmail(request.ownerEmail());
        quest.setTitle(request.title());
        quest.setDescription(request.description());
        // status și createdAt sunt setate de @PrePersist

        if (request.tasks() != null) {
            for (SaveTaskRequest taskReq : request.tasks()) {
                TaskEntity task = new TaskEntity();
                task.setTitle(taskReq.title());
                task.setDescription(taskReq.description());
                task.setQuest(quest);
                quest.getTasks().add(task);
            }
        }

        QuestEntity saved = questRepository.save(quest);
        return questMapper.entityToDto(saved);
    }

    public QuestDto accept(UUID questId, String helperEmail) {
        QuestEntity quest = questRepository.findById(questId)
                .orElseThrow(() -> new RuntimeException("Quest not found"));
        quest.setHelperEmail(helperEmail);
        quest.setStatus(QuestStatus.ACCEPTED);
        return questMapper.entityToDto(questRepository.save(quest));
    }

    public QuestDto complete(UUID questId) {
        QuestEntity quest = questRepository.findById(questId)
                .orElseThrow(() -> new RuntimeException("Quest not found"));
        quest.setStatus(QuestStatus.COMPLETED);
        return questMapper.entityToDto(questRepository.save(quest));
    }
}
