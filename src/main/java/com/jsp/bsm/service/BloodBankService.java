package com.jsp.bsm.service;

import com.jsp.bsm.requestdto.BloodBankRequest;
import com.jsp.bsm.responsedto.BloodBankResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface BloodBankService {
    BloodBankResponse addBloodBank(@Valid BloodBankRequest bankRequest);

    BloodBankResponse findBloodBankById(int bankId);

    List<BloodBankResponse> findAllBloodBankByCity(List<String> city);

    BloodBankResponse updateBloodBankById(int bankId, BloodBankRequest bankRequest);

    BloodBankResponse addAdminBank(BloodBankRequest bankRequest, int adminId);
}
