package com.application.tutorials.dto;

import com.application.tutorials.model.User;
import lombok.Data;

import java.util.UUID;

@Data
public class BasicUserInfo {
    private UUID id;

    private String firstName;

    private String lastName;

    private String email;


    private String departmentName;

    public User toEntity(){
        User newUser  = new User();
        newUser.setId(this.getId());
        if(this.email != null)
            newUser.setEmail(this.email.trim());
        if(this.firstName != null)
            newUser.setFirstName(this.firstName.trim());
        if(this.lastName != null)
            newUser.setLastName(this.lastName.trim());
        if(this.departmentName != null)
            newUser.setDepartment(this.toEntity().getDepartment());
        return newUser;
    }

    @Override
    public String toString() {
        return "BasicUserInfo {" +
                "\n\t'id' : '" + id +"'"+
                ", \n\t'firstName' : '" + firstName + '\'' +
                ", \n\t'lastName' : '" + lastName + '\'' +
                ", \n\t'email' : '" + email + '\'' +
                ", \n\t'department' : '" + departmentName + '\'' +
                "\n}";
    }
}
