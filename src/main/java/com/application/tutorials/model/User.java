package com.application.tutorials.model;

import com.application.tutorials.dto.BasicTaskInfo;
import com.application.tutorials.dto.BasicUserInfo;
import com.application.tutorials.dto.UserDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(nullable = false)
    private String email;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Department department;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Task> tasks = new ArrayList<>();

    public UserDTO toDto(){
        UserDTO newUserDto  = new UserDTO();
        newUserDto.id = this.getId();
        if(this.email != null && !this.email.trim().equalsIgnoreCase(""))
            newUserDto.setEmail(this.email.trim());
        if(this.firstName != null && !this.firstName.trim().equalsIgnoreCase(""))
            newUserDto.setFirstName(this.firstName.trim());
        if(this.lastName != null && !this.lastName.trim().equalsIgnoreCase(""))
            newUserDto.setLastName(this.lastName.trim());
        if(this.department != null){
            newUserDto.setDepartment(this.department.toDto());
            newUserDto.setDepartmentId(this.department.getId());
        }

        List<BasicTaskInfo> taskDtos = new ArrayList<>();
        if(this.tasks != null && this.tasks.size() > 0){
            this.tasks.forEach(task -> {
                taskDtos.add(task.toBasicInfo());
            });
        }

        newUserDto.setTasks(taskDtos);
        return newUserDto;
    }

    public BasicUserInfo toBasicInfo(){
        BasicUserInfo basicUserInfo  = new BasicUserInfo();
        basicUserInfo.setId(this.id);
        if(this.email != null)
            basicUserInfo.setEmail(this.email.trim());
        if(this.firstName != null)
            basicUserInfo.setFirstName(this.firstName.trim());
        if(this.lastName != null)
            basicUserInfo.setLastName(this.lastName.trim());
        if(this.department != null)
            basicUserInfo.setDepartmentName(this.department.getName());
        return basicUserInfo;
    }


//    public BasicUserInfo toBasicInfo() {
//    }
}
