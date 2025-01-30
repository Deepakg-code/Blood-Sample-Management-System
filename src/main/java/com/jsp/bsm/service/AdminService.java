package com.jsp.bsm.service;

import com.jsp.bsm.responsedto.AdminResponse;

public interface AdminService {
    AdminResponse promoteUserToAdmin(int userId);
}
