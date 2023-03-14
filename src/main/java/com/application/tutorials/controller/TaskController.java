package com.application.tutorials.controller;

import com.application.tutorials.dto.TaskDTO;
import com.application.tutorials.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/tasks")
    public TaskDTO saveTask(@RequestBody TaskDTO taskDTO){
        return taskService.saveTask(taskDTO);
    }

    @GetMapping("/tasks")
    public List<TaskDTO> getTasks(){
        return taskService.getTasks();
    }
}
