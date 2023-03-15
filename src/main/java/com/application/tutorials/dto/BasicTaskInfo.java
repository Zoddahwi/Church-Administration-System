package com.application.tutorials.dto;

import com.application.tutorials.model.Task;
import com.application.tutorials.model.User;
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
    private UUID userId;

    public Task toEntity(){
        Task task  = new Task();
        task.setId(this.id);
        if(this.description != null)
            task.setDescription(this.description.trim());
        if(this.timeLine != null)
            task.setTimeLine(ZonedDateTime.of(this.timeLine.atStartOfDay(), ZoneId.systemDefault()));
        if(this.userId != null){
            User user = new User();
            user.setId(this.userId);
            task.setUser(user);
        }
//        task.setTimeLine(ZonedDateTime.of(this.timeLine.atStartOfDay(), ZoneId.systemDefault()));


        return task;
    }
}
