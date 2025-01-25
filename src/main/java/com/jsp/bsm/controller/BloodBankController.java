package com.jsp.bsm.controller;

import com.jsp.bsm.entity.BloodBank;
import com.jsp.bsm.requestdto.BloodBankRequest;
import com.jsp.bsm.requestdto.HospitalRequest;
import com.jsp.bsm.requestdto.UserRequest;
import com.jsp.bsm.responsedto.BloodBankResponse;
import com.jsp.bsm.responsedto.HospitalResponse;
import com.jsp.bsm.responsedto.UserResponse;
import com.jsp.bsm.service.BloodBankService;
import com.jsp.bsm.utility.ResponseStructure;
import com.jsp.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BloodBankController {

    private BloodBankService bankService;
    private RestResponseBuilder responseBuilder;

    @PostMapping("/bloodbanks")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> addBloodBank(@RequestBody @Valid BloodBankRequest bankRequest){
        BloodBankResponse bankResponse = bankService.addBloodBank(bankRequest);
        return responseBuilder.success(HttpStatus.CREATED, "BloodBank Created", bankResponse);
    }

    @GetMapping("/bloodbanks/{bankId}")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> findBloodBankById(@PathVariable int bankId){
        BloodBankResponse bankResponse = bankService.findBloodBankById(bankId);
        return responseBuilder.success(HttpStatus.FOUND, "BloodBank Found", bankResponse);
    }

    @GetMapping("/blood-banks")
    public ResponseEntity<ResponseStructure<List<BloodBankResponse>>> findAllBloodBankBy(){
        List<BloodBankResponse> bankResponse = bankService.findAllBloodBank();
        return responseBuilder.success(HttpStatus.FOUND, "BloodBanks Found", bankResponse);
    }

    @PutMapping("/bloodbanks/{bankId}")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> updateBloodBankById(@PathVariable int bankId, @RequestBody @Valid BloodBankRequest bankRequest){
        BloodBankResponse bankResponse = bankService.updateBloodBankById(bankId, bankRequest);
        return responseBuilder.success(HttpStatus.FOUND, "BloodBank Updated", bankResponse);
    }

    @PostMapping("/bloodbanks-admin/{adminId}")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> addAdminBank(@RequestBody BloodBankRequest bankRequest, @PathVariable int adminId){
        BloodBankResponse bankResponse = bankService.addAdminBank(bankRequest, adminId);
        return responseBuilder.success(HttpStatus.CREATED, "Blood Bank Admin Created", bankResponse);
    }
}
