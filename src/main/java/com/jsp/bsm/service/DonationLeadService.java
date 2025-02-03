package com.jsp.bsm.service;

import com.jsp.bsm.requestdto.DonationLeadRequest;
import com.jsp.bsm.responsedto.DonationLeadResponse;

public interface DonationLeadService {
    DonationLeadResponse addDonationLead(DonationLeadRequest donationLeadRequest, int userId, int requestId);

    DonationLeadResponse findDonationLeadById(int donationId);
}
