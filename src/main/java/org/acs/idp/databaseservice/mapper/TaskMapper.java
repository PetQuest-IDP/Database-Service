package org.acs.idp.databaseservice.mapper;

import org.acs.idp.databaseservice.model.dto.TaskDto;
import org.acs.idp.databaseservice.model.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDto entityToDto(TaskEntity task) {
        return new TaskDto(
                task.getId().toString(),
                task.getQuest().getId().toString(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted(),
                task.getCompletedAt()
        );
    }
}
