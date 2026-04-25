package org.acs.idp.databaseservice.mapper;

import org.acs.idp.databaseservice.model.dto.QuestDto;
import org.acs.idp.databaseservice.model.entity.QuestEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestMapper {
    private final TaskMapper taskMapper;

    public QuestMapper(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    public QuestDto entityToDto(QuestEntity quest) {
        List<org.acs.idp.databaseservice.model.dto.TaskDto> taskDtos =
                quest.getTasks() == null ? List.of()
                        : quest.getTasks().stream().map(taskMapper::entityToDto).collect(Collectors.toList());

        return new QuestDto(
                quest.getId().toString(),
                quest.getOwnerEmail(),
                quest.getHelperEmail(),
                quest.getTitle(),
                quest.getDescription(),
                quest.getStatus(),
                quest.getCreatedAt(),
                taskDtos
        );
    }
}
