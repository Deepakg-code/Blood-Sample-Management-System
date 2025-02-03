package com.jsp.bsm.controller;

import com.jsp.bsm.requestdto.DonationRequestDTO;
import com.jsp.bsm.responsedto.DonationRequestResponse;
import com.jsp.bsm.service.DonationRequestService;
import com.jsp.bsm.utility.ResponseStructure;
import com.jsp.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DonationRequestController {

    private final DonationRequestService donationRequestService;
    private  final RestResponseBuilder responseBuilder;

    @PreAuthorize("hasAnyAuthority('OWNER_ADMIN') || hasAnyAuthority('GUEST_ADMIN')")
    @PostMapping("/donationRequest-hospital/{hospitalId}")
    public ResponseEntity<ResponseStructure<DonationRequestResponse>> addDonationRequestByHospital(@RequestBody DonationRequestDTO donationRequestDTO, @PathVariable int hospitalId) throws Exception {
        DonationRequestResponse response = donationRequestService.addDonationRequestByHospital(donationRequestDTO, hospitalId);
        return responseBuilder.success(HttpStatus.CREATED, "Donation Request Created by hospital", response);
    }

    @PreAuthorize("hasAnyAuthority('OWNER_ADMIN') || hasAnyAuthority('GUEST_ADMIN')")
    @PostMapping("/donationRequest-bloodBank/{bloodBankId}")
    public ResponseEntity<ResponseStructure<DonationRequestResponse>> addDonationRequestByBloodBank(@RequestBody DonationRequestDTO donationRequestDTO, @PathVariable int bloodBankId) throws Exception {
        DonationRequestResponse response = donationRequestService.addDonationRequestByBloodBank(donationRequestDTO, bloodBankId);
        return responseBuilder.success(HttpStatus.CREATED, "Donation Request Created by blood bank", response);
    }
}
