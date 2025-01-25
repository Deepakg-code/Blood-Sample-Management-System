package com.jsp.bsm.controller;

import com.jsp.bsm.entity.Hospital;
import com.jsp.bsm.entity.User;
import com.jsp.bsm.responsedto.AdminResponse;
import com.jsp.bsm.service.AdminService;
import com.jsp.bsm.utility.ResponseStructure;
import com.jsp.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/admins/{userId}")
    public ResponseEntity<ResponseStructure<AdminResponse>> promoteUserToAdmin(@PathVariable int userId){
        AdminResponse adminResponse = adminService.promoteUserToAdmin(userId);
        return responseBuilder.success(HttpStatus.CREATED, "Admin Created", adminResponse);

    }




}
