package com.jsp.bsm.serviceimpl;

import com.jsp.bsm.entity.Admin;
import com.jsp.bsm.entity.User;
import com.jsp.bsm.enums.AdminType;
import com.jsp.bsm.enums.Role;
import com.jsp.bsm.exception.UserNotFoundExceptionById;
import com.jsp.bsm.repository.AdminRepository;
import com.jsp.bsm.repository.UserRepository;
import com.jsp.bsm.requestdto.UserRequest;
import com.jsp.bsm.responsedto.UserResponse;
import com.jsp.bsm.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

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
        user.setPassword(userRequest.getPassword());
        user.setBloodGroup(userRequest.getBloodGroup());user.setGender(userRequest.getGender());
        user.setAvailableCity(userRequest.getAvailableCity());
        return user;
    }

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        // Mapping userRequest to user entity
        User user = this.mapToUser(userRequest, new User());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

        User updatedUser = userRepository.save(user);

        return mapToUserResponse(updatedUser);
    }

    @Override
    public UserResponse addUserAsAdmin(UserRequest userRequest) {
        User user = this.mapToUser(userRequest, new User());
        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);

        UserResponse userResponse = this.mapToUserResponse(user);
        Admin admin = Admin.builder()
                .user(user)
                .adminType(AdminType.valueOf("OWNER"))
                .build();
        adminRepository.save(admin);
        return userResponse;
    }
}
