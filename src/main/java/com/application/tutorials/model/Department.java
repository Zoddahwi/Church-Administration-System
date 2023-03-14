package com.application.tutorials.model;

import com.application.tutorials.dto.DepartmentDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue
    private UUID id;
    @Column
    private String name;

    public DepartmentDTO toDto(){
        DepartmentDTO departmentDto = new DepartmentDTO();
        departmentDto.setId(this.id);
        if(this.name != null)
            departmentDto.setName(this.name.trim());
        return departmentDto;
    }
}
