package com.application.tutorials.controller;

import com.application.tutorials.dto.DepartmentDTO;
import com.application.tutorials.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/departments")
    public DepartmentDTO saveDepartment(@RequestBody DepartmentDTO departmentDto){
        return departmentService.saveDepartment(departmentDto);
    }

    @GetMapping("/departments")
    public List<DepartmentDTO> getDepartments(){
        return departmentService.getDepartments();
    }
}
