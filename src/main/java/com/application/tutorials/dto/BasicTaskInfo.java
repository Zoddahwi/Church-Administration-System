package com.application.tutorials.dto;

import com.application.tutorials.model.Task;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class BasicTaskInfo {
    private UUID id;
    private String description;
    private LocalDate timeLine;

    public Task toEntity(){
        Task task  = new Task();
        task.setId(this.id);
        if(this.description != null)
            task.setDescription(this.description.trim());
        task.setTimeLine(ZonedDateTime.of(this.timeLine.atStartOfDay(), ZoneId.systemDefault()));

        return task;
    }
}
