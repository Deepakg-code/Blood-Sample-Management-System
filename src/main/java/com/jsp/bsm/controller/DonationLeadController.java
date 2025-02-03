package com.jsp.bsm.controller;

import com.jsp.bsm.requestdto.DonationLeadRequest;
import com.jsp.bsm.requestdto.DonationReq;
import com.jsp.bsm.responsedto.DonationLeadResponse;
import com.jsp.bsm.responsedto.DonationResponse;
import com.jsp.bsm.service.DonationLeadService;
import com.jsp.bsm.utility.ResponseStructure;
import com.jsp.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class DonationLeadController {

    private final DonationLeadService leadService;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/donationLead/{userId}/{requestId}")
    public ResponseEntity<ResponseStructure<DonationLeadResponse>> addDonationLead(@RequestBody DonationLeadRequest donationLeadRequest, @PathVariable int userId, @PathVariable int requestId){
        DonationLeadResponse donationLeadResponse = leadService.addDonationLead(donationLeadRequest, userId, requestId);
        return responseBuilder.success(HttpStatus.CREATED, "Donation Success", donationLeadResponse);
    }

    @GetMapping("/donationLead/{donationLeadId}")
    public ResponseEntity<ResponseStructure<DonationLeadResponse>> findDonationLeadById(@PathVariable int donationLeadId){
        DonationLeadResponse donationLeadResponse = leadService.findDonationLeadById(donationLeadId);
        return responseBuilder.success(HttpStatus.FOUND, "Donation lead found", donationLeadResponse);
    }
}
