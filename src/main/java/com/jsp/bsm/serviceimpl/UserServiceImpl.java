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

    private User mapToUser(UserRequest userRequest) {
        return User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .age(userRequest.getAge())
                .password(userRequest.getPassword())
                .bloodGroup(userRequest.getBloodGroup())
                .availableCity(userRequest.getAvailableCity())
                .gender(userRequest.getGender())
                .phoneNumber(userRequest.getPhoneNumber())
                .build();
    }

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        // Mapping userRequest to user entity
        User user = this.mapToUser(userRequest);
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
                .orElseThrow(() -> new UserNotFoundExceptionById("User not found"));
            exuser.setUsername(userRequest.getUsername());
            exuser.setPhoneNumber(userRequest.getPhoneNumber());
            exuser.setAge(userRequest.getAge());
            exuser.setEmail(userRequest.getEmail());
            exuser.setPassword(userRequest.getPassword());
            exuser.setBloodGroup(userRequest.getBloodGroup());
            exuser.setGender(userRequest.getGender());
            exuser.setAvailableCity(userRequest.getAvailableCity());

        User updatedUser = userRepository.save(exuser);

        return mapToUserResponse(updatedUser);
    }
}
