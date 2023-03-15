package com.application.tutorials.service;

import com.application.tutorials.dto.BasicUserInfo;
import com.application.tutorials.dto.UserDTO;
import com.application.tutorials.model.Department;
import com.application.tutorials.model.User;
import com.application.tutorials.repository.DepartmentRepository;
import com.application.tutorials.repository.UserRepository;

import com.application.tutorials.utilities.Pagination;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    private final DepartmentRepository departmentRepository;

    private final ModelMapper mapper;

    public UserDTO saveUser(UserDTO newUser){
        User user = newUser.toEntity();
        Department userDepartment = verifyDepartmentById(newUser.getDepartmentId());
        if (userDepartment == null)
            return null;
        if (doesEmailExist(newUser.getEmail()))
            return null;
        user.setDepartment(userDepartment);
        User savedUser = userRepository.save(user);
        return savedUser.toDto();
    }

    public Department verifyDepartmentById(UUID departmentId) {
        if(departmentId == null || departmentId.toString().equalsIgnoreCase("")) {
            return null;
        }
        Optional<Department> userDepartment = departmentRepository.findById(departmentId);
        if(userDepartment.isEmpty()){
            return null;
        }
        return userDepartment.get();
    }

    public Department verifyDepartmentByName(String departmentName) {
        if(departmentName == null || departmentName.equalsIgnoreCase("")) {
            return null;
        }
        List<Department> userDepartments = departmentRepository.findDepartmentsByName(departmentName.trim().toUpperCase());
        if(userDepartments.isEmpty() || userDepartments.size() > 1){
            return null;
        }
        return userDepartments.get(0);
    }
//check this for me
    public UserDTO saveUser(BasicUserInfo userInfo) {
        User user = userInfo.toEntity();
        if (doesEmailExist(userInfo.getEmail())) {
            return null;
        }
        Department userDepartment = verifyDepartmentByName(userInfo.getDepartmentName());
        if (userDepartment == null)
            return null;
        user.setDepartment(userDepartment);
        User savedUser = userRepository.save(user);
        return savedUser.toDto();
    }

    public boolean doesEmailExist(String email) {
        if (email == null || email.equalsIgnoreCase("")){
            return true;
        }
        Integer emailCount = userRepository.countUsersByEmailIsLike(email.trim());
        if(emailCount > 0){
            return true;
        }
        return false;
    }

    public List<UserDTO> getUsers(){
        List<User> users = userRepository.findAll();
        List<UserDTO> userDtoList = new ArrayList<>();
        if(!users.isEmpty()){
            users.forEach(user -> {
                userDtoList.add(user.toDto());
            });
        }
        return userDtoList;
    }

    public UserDTO getUser(UUID id){
        if(id != null){
            Optional<User> user = userRepository.findById(id);
            if(user.isPresent()){
                return user.get().toDto();
            }
        }
        return null;
    }

    public User getUserToEntity(UUID id){
        if(id != null){
            Optional<User> user = userRepository.findById(id);
            if(user.isPresent()){
                return user.get();
            }
        }
        return null;
    }




    public String deleteUser(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            return "User does not exist";
        }
        userRepository.deleteById(id);
        return "User '" + user.get().getFirstName() + " " + user.get().getLastName() + "' has been deleted successfully";
    }

    public String updateUser(UUID id, Map<String, String> userDto) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            return "User does not exist";
        }
        User fetchedUser = user.get();

        if(userDto.getOrDefault("email", "") != "" && !userDto.getOrDefault("email", "").trim().equalsIgnoreCase("")){
            fetchedUser.setEmail(userDto.get("email"));
        }
        if(userDto.getOrDefault("firstName", "") != "" && !userDto.getOrDefault("firstName", "").trim().equalsIgnoreCase("")){
            fetchedUser.setFirstName(userDto.get("firstName"));
        }
        if(userDto.getOrDefault("lastName", "") != "" && !userDto.getOrDefault("lastName", "").trim().equalsIgnoreCase("")){
            fetchedUser.setLastName(userDto.get("lastName"));
        }
        /* VERIFY DEPARTMENT */
        Department newDepartment = null;
        if(userDto.getOrDefault("departmentName", "") != "" && !userDto.getOrDefault("departmentName", "").trim().equalsIgnoreCase("")){
            newDepartment = verifyDepartmentByName(userDto.get("departmentName"));
        }else if(userDto.getOrDefault("departmentId", "") != "" && !userDto.getOrDefault("departmentId", "").trim().equalsIgnoreCase("")){
            newDepartment = verifyDepartmentById(UUID.fromString(userDto.get("departmentId")));
        }
        if(newDepartment != null){
            fetchedUser.setDepartment(newDepartment);
        }

        User savedUser = userRepository.save(fetchedUser);

        return "User has been updated successfully \n\n" + savedUser.toDto().toString();
    }
    public Pagination<UserDTO> getUsersPagination(int pageNo, int pageSize, String sortBy){
        Pageable pageable = (Pageable) PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<User> page = userRepository.findAll(pageable);

        Pagination<UserDTO> response = getUserDtoPagination(page);
        return response;
    }

    public Pagination<UserDTO> getUserDtoPagination(Page<User> page) {
        Pagination<UserDTO> response = new Pagination<>();
        List<UserDTO> userDtoList = new ArrayList<>();
        page.getContent().forEach(user -> {
            userDtoList.add(user.toDto());
        });
        response.setContent(userDtoList);
        response.setPageNo(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements((int) page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setHasNext(page.hasNext());
        response.setHasPrevious(page.hasPrevious());
        return response ;
    }

    public List<User> saveUsers(List<UserDTO> userDtoList) {
        List<User> users = new ArrayList<>();
        userDtoList.forEach(userDto -> {
            users.add(userDto.toEntity());
        });
        return userRepository.saveAll(users);
    }
}
