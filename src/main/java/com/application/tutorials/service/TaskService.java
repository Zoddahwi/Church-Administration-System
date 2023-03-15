package com.application.tutorials.service;

import com.application.tutorials.dto.BasicTaskInfo;
import com.application.tutorials.dto.TaskDTO;
import com.application.tutorials.model.Task;
import com.application.tutorials.model.User;
import com.application.tutorials.repository.TaskRepository;
import com.application.tutorials.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public TaskDTO saveTask(BasicTaskInfo taskInfo) {
        Task task = taskInfo.toEntity();
        if(taskInfo.getUserId() == null || taskInfo.getUserId().toString().equalsIgnoreCase("")){
            return null;
        }
        Optional<User> userExists = userRepository.findById(taskInfo.getUserId());
        if(userExists.isEmpty()){
            return null;
        }
        task.setUser(userExists.get());
        Task savedTask = taskRepository.save(task);
        return savedTask.toDto();
    }



    private User verifyUserById(UUID userId) {
        if(userId == null || userId.toString().equalsIgnoreCase("")){
            return null;
        }
        Optional<User> userExists = userRepository.findById(userId);
        if(userExists.isEmpty()){
            return null;
        }
        return userExists.get();
    }

    public List<TaskDTO> getTasks() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskDTO> response = new ArrayList<>();
        tasks.forEach(task -> {
            response.add(task.toDto());
        });
        return response;
    }

}
