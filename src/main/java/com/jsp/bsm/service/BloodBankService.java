package com.jsp.bsm.service;

import com.jsp.bsm.enums.BloodGroup;
import com.jsp.bsm.requestdto.BloodBankRequest;
import com.jsp.bsm.responsedto.BloodBankPageResponse;
import com.jsp.bsm.responsedto.BloodBankResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface BloodBankService {

    BloodBankResponse findBloodBankById(int bankId);

    List<BloodBankPageResponse> findAllBloodBankByCity(List<String> city, List<BloodGroup> bloodGroup, int page, int size);

    BloodBankResponse updateBloodBankById(int bankId, BloodBankRequest bankRequest);

    BloodBankResponse addAdminBank(BloodBankRequest bankRequest, int adminId);
}
