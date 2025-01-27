package com.jsp.bsm.service;

import com.jsp.bsm.requestdto.UserRequest;
import com.jsp.bsm.responsedto.UserResponse;
import jakarta.validation.Valid;

public interface UserService {

   public UserResponse addUser(UserRequest userRequest);

   public UserResponse findUserById();

   public UserResponse updateUserById(UserRequest userRequest);

   UserResponse addUserAsAdmin(@Valid UserRequest userRequest);

    UserResponse verifyStatus(int userId, boolean isVerified);
}
