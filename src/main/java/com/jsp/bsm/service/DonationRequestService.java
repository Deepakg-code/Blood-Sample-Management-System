package com.jsp.bsm.service;

import com.jsp.bsm.requestdto.DonationRequestDTO;
import com.jsp.bsm.responsedto.DonationRequestResponse;

public interface DonationRequestService {
    DonationRequestResponse addDonationRequestByHospital(DonationRequestDTO donationRequestDTO, int hospitalId) throws Exception;

    DonationRequestResponse addDonationRequestByBloodBank(DonationRequestDTO donationRequestDTO, int bloodBankId) throws Exception;
}
