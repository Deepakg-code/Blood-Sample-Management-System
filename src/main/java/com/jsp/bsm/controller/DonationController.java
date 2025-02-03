package com.jsp.bsm.controller;

import com.jsp.bsm.requestdto.DonationReq;
import com.jsp.bsm.responsedto.DonationResponse;
import com.jsp.bsm.service.DonationService;
import com.jsp.bsm.utility.ResponseStructure;
import com.jsp.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DonationController {

    private final DonationService donationService;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/donation/{requestId}")
    public ResponseEntity<ResponseStructure<DonationResponse>> addDonation(@RequestBody DonationReq donationReq, @PathVariable int requestId){
        DonationResponse donationResponse = donationService.addDonation(donationReq, requestId);
        return responseBuilder.success(HttpStatus.CREATED, "Donation Success", donationResponse);
    }

    @PutMapping("/donation/{donationId}")
    public ResponseEntity<ResponseStructure<DonationResponse>> updateDonation(@RequestBody DonationReq donationReq, @PathVariable int donationId){
        DonationResponse donationResponse = donationService.updateDonation(donationReq, donationId);
        return responseBuilder.success(HttpStatus.OK, "Donation update successful", donationResponse);
    }

    @GetMapping("/donations/{requestId}")
    public ResponseEntity<ResponseStructure<List<DonationResponse>>> findAllDonationByDonationRequest(@PathVariable int requestId){
        List<DonationResponse> donationResponseList = donationService.findAllDonationByDonationRequest(requestId);
        return responseBuilder.success(HttpStatus.FOUND, "Donation found", donationResponseList);
    }
}
