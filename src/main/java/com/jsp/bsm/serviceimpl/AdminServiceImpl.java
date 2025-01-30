package com.jsp.bsm.serviceimpl;

import com.jsp.bsm.entity.Admin;
import com.jsp.bsm.entity.User;
import com.jsp.bsm.enums.Role;
import com.jsp.bsm.exception.UserNotFoundExceptionById;
import com.jsp.bsm.repository.AdminRepository;
import com.jsp.bsm.repository.HospitalRepository;
import com.jsp.bsm.repository.UserRepository;
import com.jsp.bsm.responsedto.AdminResponse;
import com.jsp.bsm.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final HospitalRepository hospitalRepository;


    @Override
    public AdminResponse promoteUserToAdmin(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptionById("User Not Found"));
        user.setRole(Role.GUEST_ADMIN);
        userRepository.save(user);

        Admin admin = Admin.builder()
                .user(user)
                .build();
        Admin savedAdmin = adminRepository.save(admin);

        return AdminResponse.builder()
                .adminId(savedAdmin.getAdminId())
                .user(user)
                .build();
    }

}
