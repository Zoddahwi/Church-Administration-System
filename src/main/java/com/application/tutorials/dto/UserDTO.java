package com.application.tutorials.dto;

import com.application.tutorials.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class UserDTO {
    public UUID id;

    public String firstName;

    public String lastName;

    public String email;

    public DepartmentDTO department;

    public UUID departmentId;

    public List<BasicTaskInfo> tasks = new ArrayList<>();

    public User toEntity(){
        User newUser  = new User();
        newUser.setId(this.getId());
        if(this.email != null)
            newUser.setEmail(this.email.trim());
        if(this.firstName != null)
            newUser.setFirstName(this.firstName.trim());
        if(this.lastName != null)
            newUser.setLastName(this.lastName.trim());
        if(this.department != null)
            newUser.setDepartment(this.department.toEntity());

        return newUser;
    }

    @Override
    public String toString() {
        return "UserDto {" +
                "\n\t'id' : '" + id +"'"+
                ", \n\t'firstName' : '" + firstName + '\'' +
                ", \n\t'lastName' : '" + lastName + '\'' +
                ", \n\t'email' : '" + email + '\'' +
                ", \n\t'department' : '" + department + '\'' +
                "\n}";
    }
}
