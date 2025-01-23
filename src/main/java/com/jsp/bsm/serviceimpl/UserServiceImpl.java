package com.jsp.bsm.serviceimpl;

import com.jsp.bsm.entity.User;
import com.jsp.bsm.exception.UserNotFoundExceptionById;
import com.jsp.bsm.repository.UserRepository;
import com.jsp.bsm.request.UserRequest;
import com.jsp.bsm.response.UserResponse;
import com.jsp.bsm.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .bloodGroup(user.getBloodGroup())
                .lastDonatedAt(user.getLastDonatedAt())
                .age(user.getAge())
                .gender(user.getGender())
                .availableCity(user.getAvailableCity())
                .verified(user.isVerified())
                .build();
    }

    private User mapToUser(UserRequest userRequest, User user) {
        user.setUsername(userRequest.getUsername());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setAge(userRequest.getAge());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setBloodGroup(userRequest.getBloodGroup());user.setGender(userRequest.getGender());
        user.setAvailableCity(userRequest.getAvailableCity());
        return user;
    }

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        // Mapping userRequest to user entity
        User user = this.mapToUser(userRequest, new User());
        user = userRepository.save(user);

        return this.mapToUserResponse(user);
    }

    @Override
    public UserResponse findUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptionById("User Not Found"));
            return mapToUserResponse(user);

    }

    @Override
    public UserResponse updateUserById(int userId, UserRequest userRequest) {
        User exuser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptionById("Failed to update"));

        User user = this.mapToUser(userRequest, exuser);

        User updatedUser = userRepository.save(exuser);

        return mapToUserResponse(updatedUser);
    }
}
