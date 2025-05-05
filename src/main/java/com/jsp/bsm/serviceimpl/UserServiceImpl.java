package com.jsp.bsm.serviceimpl;

import com.jsp.bsm.entity.Admin;
import com.jsp.bsm.entity.User;
import com.jsp.bsm.enums.Role;
import com.jsp.bsm.exception.UserNotFoundExceptionById;
import com.jsp.bsm.repository.AdminRepository;
import com.jsp.bsm.repository.UserRepository;
import com.jsp.bsm.requestdto.UserRequest;
import com.jsp.bsm.responsedto.UserResponse;
import com.jsp.bsm.security.AuthUtil;
import com.jsp.bsm.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthUtil authUtil;

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
                .role(user.getRole())
                .build();
    }

    private User mapToUser(UserRequest userRequest, User user) {
        user.setUsername(userRequest.getUsername());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setAge(userRequest.getAge());
        user.setEmail(userRequest.getEmail());
        user.setBloodGroup(userRequest.getBloodGroup());
        user.setGender(userRequest.getGender());
        user.setAvailableCity(userRequest.getAvailableCity());
        user.setLastDonatedAt(userRequest.getLastDonatedAt());

        // Encode password if it has been changed
        if (!userRequest.getPassword().equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }

        return user;
    }

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        User user = this.mapToUser(userRequest, new User());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);

        return this.mapToUserResponse(user);
    }

    @Override
    public UserResponse findUserById() {
        User user = authUtil.getCurrentUser();
        return mapToUserResponse(user);
    }

    @Override
    public UserResponse updateUserById(UserRequest userRequest) {
        User exuser = authUtil.getCurrentUser();
        User user = this.mapToUser(userRequest, exuser);
        User updatedUser = userRepository.save(user);
        return mapToUserResponse(updatedUser);
    }

    @Override
    public UserResponse addUserAsAdmin(UserRequest userRequest) {
        User user = this.mapToUser(userRequest, new User());
        user.setRole(Role.OWNER_ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);

        UserResponse userResponse = this.mapToUserResponse(user);
        Admin admin = Admin.builder()
                .user(user)
                .build();
        adminRepository.save(admin);
        return userResponse;
    }

    @Override
    public UserResponse verifyStatus(int userId, boolean status) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptionById("Failed to find the user"));
        user.setVerified(status);
        userRepository.save(user);
        return this.mapToUserResponse(user);
    }

    @Override
    public UserResponse lastDonatedAt(LocalDate lastDonatedAt) {
        User user = authUtil.getCurrentUser();
        user.setLastDonatedAt(lastDonatedAt);
        User updatedUser = userRepository.save(user);
        return this.mapToUserResponse(updatedUser);
    }
}