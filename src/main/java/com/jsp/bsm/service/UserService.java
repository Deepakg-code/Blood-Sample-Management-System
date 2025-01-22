package com.jsp.bsm.service;

import com.jsp.bsm.entity.User;

public interface UserService {

   public User addUser(User user);

   public User findUserById(int userId);

   public User updateUserById(int userId, User user);

}
