package com.jsp.bsm.controller;

import com.jsp.bsm.entity.Transaction;
import com.jsp.bsm.requestdto.HospitalRequest;
import com.jsp.bsm.requestdto.TransactionRequest;
import com.jsp.bsm.responsedto.HospitalResponse;
import com.jsp.bsm.responsedto.TransactionResponse;
import com.jsp.bsm.service.TransactionService;
import com.jsp.bsm.utility.ResponseStructure;
import com.jsp.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final RestResponseBuilder responseBuilder;

//    @PostMapping("/transaction-user")
//    public ResponseEntity<ResponseStructure<TransactionResponse>> addUserTransaction(@RequestBody TransactionRequest transactionRequest){
//        TransactionResponse transactionResponse = transactionService.addUserTransaction(transactionRequest);
//        return responseBuilder.success(HttpStatus.CREATED, "User Transaction Created", transactionResponse);
//    }
//
//    @PreAuthorize("hasAnyAuthority('OWNER_ADMIN') || hasAnyAuthority('GUEST_ADMIN')")
//    @PostMapping("/transaction-hospital")
//    public ResponseEntity<ResponseStructure<TransactionResponse>> addHospitalTransaction(@RequestBody TransactionRequest transactionRequest, @PathVariable int hospitalId){
//        TransactionResponse transactionResponse = transactionService.addHospitalTransaction(transactionRequest, hospitalId);
//        return responseBuilder.success(HttpStatus.CREATED, "Hospital Transaction Created", transactionResponse);
//    }

    @PreAuthorize("hasAnyAuthority('OWNER_ADMIN') || hasAnyAuthority('GUEST_ADMIN')")
    @PostMapping("/transactions/{hospitalId}/{userId}")
    public ResponseEntity<ResponseStructure<TransactionResponse>> checkTransaction(@RequestBody TransactionRequest transactionRequest, @PathVariable int hospitalId, @PathVariable int userId) throws Exception {
        TransactionResponse transactionResponse = transactionService.checkTransaction(transactionRequest, hospitalId, userId);
        return responseBuilder.success(HttpStatus.CREATED, "Transaction Successful", transactionResponse);
    }

}
