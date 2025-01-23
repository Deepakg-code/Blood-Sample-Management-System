package com.jsp.bsm.service;

import com.jsp.bsm.requestdto.UserRequest;
import com.jsp.bsm.responsedto.UserResponse;

public interface UserService {

   public UserResponse addUser(UserRequest userRequest);

   public UserResponse findUserById(int userId);

   public UserResponse updateUserById(int userId, UserRequest userRequest);

}
