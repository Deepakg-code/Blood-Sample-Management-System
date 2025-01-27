package com.jsp.bsm.controller;

import com.jsp.bsm.entity.Address;
import com.jsp.bsm.requestdto.AddressRequest;
import com.jsp.bsm.responsedto.AddressResponse;
import com.jsp.bsm.responsedto.AdminResponse;
import com.jsp.bsm.service.AddressService;
import com.jsp.bsm.utility.ResponseStructure;
import com.jsp.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/address")
    public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(@RequestBody AddressRequest addressRequest){
        AddressResponse addressResponse = addressService.addAddress(addressRequest);
        return responseBuilder.success(HttpStatus.CREATED, "Address Created", addressResponse);

    }
}
