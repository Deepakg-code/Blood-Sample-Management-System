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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    @GetMapping("/users")
    public ResponseEntity<ResponseStructure<UserResponse>> findUserById(){
        UserResponse userResponse = userService.findUserById();
        return responseBuilder.success(HttpStatus.FOUND, "User Found", userResponse);
    }

    @PutMapping("/users")
    public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@RequestBody @Valid UserRequest userRequest){
        UserResponse userResponse = userService.updateUserById(userRequest);
        return responseBuilder.success(HttpStatus.OK, "User updated", userResponse);
    }


    @PostMapping("/register-admin")
    public ResponseEntity<ResponseStructure<UserResponse>> addUserAsAdmin(@RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.addUserAsAdmin(userRequest);
        return responseBuilder.success(HttpStatus.CREATED, "User Created", userResponse);
    }

    @PreAuthorize("hasAnyAuthority('OWNER_ADMIN') || hasAnyAuthority('GUEST_ADMIN')")
    @PatchMapping("/users/{userId}")
    public ResponseEntity<ResponseStructure<UserResponse>> verifyStatus(@PathVariable int userId, @RequestParam boolean isVerified) {
        UserResponse userResponse = userService.verifyStatus(userId, isVerified);
        return responseBuilder.success(HttpStatus.OK,"Status Updated", userResponse);
    }
    @PatchMapping("/users-lastDonate")
    public ResponseEntity<ResponseStructure<UserResponse>> updateLastDonatedAt(@RequestParam LocalDate lastDonatedAt){
        UserResponse userResponse = userService.lastDonatedAt(lastDonatedAt);
        return responseBuilder.success(HttpStatus.OK,"Last Donated Date Updated", userResponse);
    }
}
