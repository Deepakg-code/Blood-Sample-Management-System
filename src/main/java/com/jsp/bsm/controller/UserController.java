package com.jsp.bsm.controller;

import com.jsp.bsm.requestdto.UserRequest;
import com.jsp.bsm.responsedto.UserResponse;
import com.jsp.bsm.service.UserService;
import com.jsp.bsm.utility.ResponseStructure;
import com.jsp.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {


    private final UserService userService;

    private  final RestResponseBuilder responseBuilder;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserResponse>> addUser(@RequestBody @Valid UserRequest userRequest){
        UserResponse userResponse = userService.addUser(userRequest);
        return responseBuilder.success(HttpStatus.CREATED, "User Created", userResponse);
    }

    @GetMapping("/users/{userid}")
    public ResponseEntity<ResponseStructure<UserResponse>> findUserById(@PathVariable("userid") int userId){
        UserResponse userResponse = userService.findUserById(userId);
        return responseBuilder.success(HttpStatus.FOUND, "User Found", userResponse);
    }

    @PutMapping("/users/{userid}")
    public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@PathVariable("userid") int userId, @RequestBody @Valid UserRequest userRequest){
        UserResponse userResponse = userService.updateUserById(userId, userRequest);
        return responseBuilder.success(HttpStatus.OK, "User updated", userResponse);
    }


    @PostMapping("/register-admin")
    public ResponseEntity<ResponseStructure<UserResponse>> addUserAsAdmin(@RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.addUserAsAdmin(userRequest);
        return responseBuilder.success(HttpStatus.CREATED, "User Created", userResponse);
    }

}
