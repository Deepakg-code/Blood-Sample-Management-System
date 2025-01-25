package com.jsp.bsm.service;

import com.jsp.bsm.entity.BloodBank;
import com.jsp.bsm.requestdto.BloodBankRequest;
import com.jsp.bsm.requestdto.UserRequest;
import com.jsp.bsm.responsedto.BloodBankResponse;
import com.jsp.bsm.responsedto.UserResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface BloodBankService {
    BloodBankResponse addBloodBank(@Valid BloodBankRequest bankRequest);

    BloodBankResponse findBloodBankById(int bankId);

    List<BloodBankResponse> findAllBloodBank();

    BloodBankResponse updateBloodBankById(int bankId, BloodBankRequest bankRequest);

    BloodBankResponse addAdminBank(BloodBankRequest bankRequest, int adminId);
}
