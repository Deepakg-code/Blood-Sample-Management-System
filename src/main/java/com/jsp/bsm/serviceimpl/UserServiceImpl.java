package com.jsp.bsm.serviceimpl;

import com.jsp.bsm.entity.User;
import com.jsp.bsm.exception.UserNotFoundExceptionById;
import com.jsp.bsm.repository.UserRepository;
import com.jsp.bsm.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(int userId) {
        Optional<User> optional = userRepository.findById(userId);
        if (optional.isEmpty())
            throw new UserNotFoundExceptionById("User Not Found");
        return optional.get();

    }

    @Override
    public User updateUserById(int userId, User user) {
        Optional<User> optional = userRepository.findById(userId);
        if(optional.isPresent()){
            User exuser = optional.get();
            exuser.setUsername(user.getUsername());
            exuser.setPhoneNumber(user.getPhoneNumber());
            exuser.setAge(user.getAge());
            exuser.setEmail(user.getEmail());
            exuser.setPassword(user.getPassword());
            exuser.setBloodGroup(user.getBloodGroup());
            exuser.setGender(user.getGender());
            exuser.setAvailableCity(user.getAvailableCity());
            exuser.setVerified(user.isVerified());
            return userRepository.save(exuser);
        }
        else {
            throw new UserNotFoundExceptionById("User Not Get Updated");
        }
    }
}
