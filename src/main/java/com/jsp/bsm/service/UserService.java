package com.jsp.bsm.service;

import com.jsp.bsm.entity.User;
import com.jsp.bsm.request.UserRequest;
import com.jsp.bsm.response.UserResponse;

public interface UserService {

   public UserResponse addUser(UserRequest userRequest);

   public UserResponse findUserById(int userId);

   public UserResponse updateUserById(int userId, UserRequest userRequest);

}
