package com.application.tutorials.dto;

import com.application.tutorials.model.Task;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class TaskDTO {

    private UUID id;
    private String description;
    private LocalDate timeLine;
    private BasicUserInfo user;

    private UUID userId;

    public Task toEntity(){
        Task task  = new Task();
        task.setId(this.id);
        if(this.description != null)
            task.setDescription(this.description.trim());
        task.setTimeLine(ZonedDateTime.of(this.timeLine.atStartOfDay(), ZoneId.systemDefault()));

        return task;
    }
}