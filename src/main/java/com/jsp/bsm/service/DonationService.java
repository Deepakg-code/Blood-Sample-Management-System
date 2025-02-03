package com.jsp.bsm.service;

import com.jsp.bsm.requestdto.DonationReq;
import com.jsp.bsm.responsedto.DonationResponse;

import java.util.List;

public interface DonationService {
    DonationResponse addDonation(DonationReq donationReq, int requestId);

    DonationResponse updateDonation(DonationReq donationReq, int donationId);

    List<DonationResponse> findAllDonationByDonationRequest(int requestId);
}
