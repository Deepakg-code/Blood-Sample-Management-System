package com.jsp.bsm.controller;

import com.jsp.bsm.entity.User;
import com.jsp.bsm.service.UserService;
import com.jsp.bsm.utility.ResponseStructure;
import com.jsp.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {


    private final UserService userService;

    private  final RestResponseBuilder responseBuilder;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<User>> addUser(@RequestBody User user){
        user = userService.addUser(user);
        return responseBuilder.success(HttpStatus.CREATED, "User Created", user);
    }

    @GetMapping("/users/{userid}")
    public ResponseEntity<ResponseStructure<User>> findUserById(@PathVariable("userid") int userId){
        User user = userService.findUserById(userId);
        return responseBuilder.success(HttpStatus.FOUND, "User Found", user);
    }

    @PutMapping("/users/{userid}")
    public ResponseEntity<ResponseStructure<User>> updateUser(@PathVariable("userid") int userId, @RequestBody User user){
        User user1 = userService.updateUserById(userId, user);
        return responseBuilder.success(HttpStatus.OK, "User updated", user1);
    }


}
