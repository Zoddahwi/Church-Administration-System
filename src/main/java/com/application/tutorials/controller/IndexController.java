package com.application.tutorials.controller;


import com.application.tutorials.dto.UserDTO;
import com.application.tutorials.model.User;
import com.application.tutorials.service.UserService;
import com.application.tutorials.utilities.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//@RestController
@Controller
@ResponseBody
public class IndexController {

//   @Autowired
//    private UserService userService;
//
//    @GetMapping("/version")
//    public String getVersion(){
//        return "version 1.0";
//    }
//
//    @GetMapping("/is-active")
//    public Boolean getIsActive(){
//        return true;
//    }
//
//    @GetMapping("/users")
//    public List getUsers(){
//        return userService.getUsers();
//    }
//
//    @GetMapping("/users/paginate")
//    public Pagination getUsersPagination(@RequestParam(value = "pageNo" , defaultValue =  "0") int pageNo,
//                                         @RequestParam(value = "pageSize" , defaultValue =  "10") int pageSize,
//                                         @RequestParam(value = "sortBy" , defaultValue =  "id") String sortBy){
//        if(pageNo < 0){
//            pageNo = 0;
//        }
//        if(pageSize < 1){
//            pageSize = 10;
//        }
//        return userService.getUsersPagination(pageNo,pageSize,sortBy);
//    }
//    /*
//     * PathVariable
//     * RequestParam
//     * RequestBody
//     * */
//
//    @GetMapping("/users/{id}")
//    public UserDTO getUser(@PathVariable(name = "id") UUID id){
//        System.out.println("User id : "+ id);
//        return userService.getUser(id);
//    }
//
//    @GetMapping("/users/{id}/status")
//    public String getUserStatus(@PathVariable(name = "id") String id, @RequestParam Map<String,String> status){
//        return "Id : " + id + "\nStatus : "+ status.toString();
//    }
//
//    @PostMapping("/users/")
//    public UserDTO saveUser(@RequestBody UserDTO userDto){
//        return userService.saveUser(userDto);
//    }
//
//    @PostMapping("/users/list")
//   public List<User> saveUsers(@RequestBody List<UserDTO> userDtoList){
//
//        return userService.saveUsers(userDtoList);
//    }
//
//    @DeleteMapping("/users/{id}")
//    public String deleteUser(@PathVariable(name = "id") UUID id){
//       return userService.deleteUser(id);
//    }
//
////    @PatchMapping("/users/{id}")
////    public String updateUser(@PathVariable(name = "id") UUID id, @RequestBody UserDTO userDto){
////        return userService.updateUser(id, userDto);
////    }
//
////    @PatchMapping("/users/")
////    public String updateUser(UserDTO userDto){
////        return "User updated successfully";
////    }
//
////    @GetMapping("/save-user")
////    public User saveUser(){
////        User newUser = new User();
////        newUser.setFirstName("Zoddah");
////        newUser.setLastName("Wise");
////        newUser.setEmail("zoddahwi@stlghana.com");
////        return userService.saveUser(newUser);
////    }
}
