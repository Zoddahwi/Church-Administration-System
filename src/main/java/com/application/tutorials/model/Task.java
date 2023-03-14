package com.application.tutorials.model;

import com.application.tutorials.dto.BasicTaskInfo;
import com.application.tutorials.dto.TaskDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String description;

    @Column
    private ZonedDateTime timeLine;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    public TaskDTO toDto(){
        TaskDTO taskDto  = new TaskDTO();
        taskDto.setId(this.id);
        if(this.description != null)
            taskDto.setDescription(this.description.trim());
        if(this.timeLine != null && !this.timeLine.toString().equalsIgnoreCase("")){
            taskDto.setTimeLine(this.timeLine.toLocalDate());
        }
        if(this.user != null){
            taskDto.setUser(this.user.toBasicInfo());
            taskDto.setUserId(this.user.getId());
        }
        return taskDto;
    }


    public BasicTaskInfo toBasicInfo() {
        BasicTaskInfo basicTaskInfo  = new BasicTaskInfo();
        basicTaskInfo.setId(this.id);
        if(this.description != null)
            basicTaskInfo.setDescription(this.description.trim());
        if(this.timeLine != null && !this.timeLine.toString().equalsIgnoreCase("")){
            basicTaskInfo.setTimeLine(this.timeLine.toLocalDate());
        }
        return basicTaskInfo;
    }
}
