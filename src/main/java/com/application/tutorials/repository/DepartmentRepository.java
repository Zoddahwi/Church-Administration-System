package com.application.tutorials.repository;

import com.application.tutorials.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {

    @Query("select d from Department d where UPPER(d.name) = UPPER(:name)")
    List<Department> findDepartmentsByName (String name);
}
