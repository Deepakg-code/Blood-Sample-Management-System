package com.jsp.bsm.service;

import com.jsp.bsm.enums.BloodGroup;
import com.jsp.bsm.requestdto.BloodBankRequest;
import com.jsp.bsm.responsedto.BloodBankResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface BloodBankService {

    BloodBankResponse findBloodBankById(int bankId);

    List<BloodBankResponse> findAllBloodBankByCity(List<String> city, BloodGroup bloodGroup);

    BloodBankResponse updateBloodBankById(int bankId, BloodBankRequest bankRequest);

    BloodBankResponse addAdminBank(BloodBankRequest bankRequest, int adminId);
}
