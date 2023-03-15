package com.application.tutorials.controller;

import com.application.tutorials.dto.BasicTaskInfo;
import com.application.tutorials.dto.TaskDTO;
import com.application.tutorials.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/tasks")
    public TaskDTO saveTask(@RequestBody BasicTaskInfo  taskInfo){
        return taskService.saveTask(taskInfo);
    }

    @GetMapping("/tasks")
    public List<TaskDTO> getTasks(){
        return taskService.getTasks();
    }
}
