package com.application.tutorials.dto;

import com.application.tutorials.model.Department;
import lombok.Data;

import java.util.UUID;

@Data
public class DepartmentDTO {
    private UUID id;
    private String name;

    public Department toEntity(){
        Department department = new Department();
        department.setId(this.id);
        if(this.name != null)
            department.setName(this.name.trim());
        return department;
    }
}
