package com.application.tutorials.service;

import com.application.tutorials.dto.DepartmentDTO;
import com.application.tutorials.model.Department;
import com.application.tutorials.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentDTO saveDepartment(DepartmentDTO departmentDto) {
        if(departmentDto.getName() == null || departmentDto.getName().trim().equalsIgnoreCase("")){
            return null;
        }
        var departmentExist = departmentRepository.findDepartmentsByName(departmentDto.getName().trim());
        if(!departmentExist.isEmpty()){
            return null;
        }
        Department newDepartment = new Department();
        newDepartment.setName(departmentDto.getName().trim());
        Department savedDepartment = departmentRepository.save(newDepartment);
        return savedDepartment.toDto();
    }

    public List<DepartmentDTO> getDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDTO> response = new ArrayList<>();
        departments.forEach(department -> {
            response.add(department.toDto());
        });
        return response;
    }

}
