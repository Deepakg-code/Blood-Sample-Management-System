package com.jsp.bsm.controller;

import com.jsp.bsm.entity.User;
import com.jsp.bsm.service.UserService;
import com.jsp.bsm.utility.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    public ResponseEntity<ResponseStructure<User>> addUser(@RequestBody User user){
        user = userService.addUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseStructure.create(HttpStatus.CREATED.value(), "User Created", user));
    }

}
