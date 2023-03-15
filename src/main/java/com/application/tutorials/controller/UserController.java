package com.application.tutorials.controller;

import com.application.tutorials.dto.BasicUserInfo;
import com.application.tutorials.dto.UserDTO;
import com.application.tutorials.service.UserService;
import com.application.tutorials.utilities.Pagination;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@ResponseBody
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;



    @GetMapping("/version")
    public String getVersion(){
        return "version 1.0";
    }

    @GetMapping("/is-active")
    public Boolean getIsActive(){
        return true;
    }

    @GetMapping("/users")
    public List getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/users/paginate")
    public Pagination<UserDTO> getUsersPagination(@RequestParam(value = "pageNo" , defaultValue =  "0") int pageNo,
                                                  @RequestParam(value = "pageSize" , defaultValue =  "10") int pageSize,
                                                  @RequestParam(value = "sortBy" , defaultValue =  "id") String sortBy){
        if(pageNo < 0){
            pageNo = 0;
        }
        if(pageSize < 1){
            pageSize = 10;
        }
        return userService.getUsersPagination(pageNo,pageSize,sortBy);
    }
    /*
     * PathVariable
     * RequestParam
     * RequestBody
     * */

    @GetMapping("/users/{id}")
    public UserDTO getUser(@PathVariable(name = "id") UUID id){
        System.out.println("User id : "+ id);
        return userService.getUser(id);
    }

    @GetMapping("/users/{id}/status")
    public String getUserStatus(@PathVariable(name = "id") String id, @RequestParam Map<String,String> status){
        return "Id : " + id + "\nStatus : "+ status.toString();
    }

    @PostMapping("/users")
    public UserDTO saveUser(@RequestBody Map<String, String> userInfo){
        if(!userInfo.getOrDefault("departmentName", "").equals("")) {
            BasicUserInfo basicUserInfo = mapper.map(userInfo, BasicUserInfo.class);
            return userService.saveUser(basicUserInfo);
        }else{
            UserDTO userDto = mapper.map(userInfo, UserDTO.class);
            userDto.setDepartmentId(UUID.fromString(userInfo.get("departmentId")));
            return userService.saveUser(userDto);
        }
    }

    @DeleteMapping("users/{id}")
    public String deleteUser(@PathVariable(name = "id") UUID id){
        return userService.deleteUser(id);
    }

    @PatchMapping("/users/{id}")
    public String updateUser(@PathVariable("id") UUID id, @RequestBody Map<String, String> userInfo){
        return userService.updateUser(id, userInfo);
    }







}
