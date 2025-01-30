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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/address-user")
    public ResponseEntity<ResponseStructure<AddressResponse>> addUserAddress(@RequestBody AddressRequest addressRequest){
        AddressResponse addressResponse = addressService.addUserAddress(addressRequest);
        return responseBuilder.success(HttpStatus.CREATED, "User Address Created", addressResponse);
    }

    @PreAuthorize("hasAnyAuthority('OWNER_ADMIN') || hasAnyAuthority('GUEST_ADMIN')")
    @PostMapping("/address-hospital/{hospitalId}")
    public ResponseEntity<ResponseStructure<AddressResponse>> addHospitalAddress(@RequestBody AddressRequest addressRequest, @PathVariable int hospitalId){
        AddressResponse addressResponse = addressService.addHospitalAddress(addressRequest, hospitalId);
        return responseBuilder.success(HttpStatus.CREATED, "Hospital Address Created", addressResponse);
    }

    @PreAuthorize("hasAnyAuthority('OWNER_ADMIN') || hasAnyAuthority('OWNER_ADMIN')")
    @PostMapping("/address-bloodbank/{bloodbankId}")
    public ResponseEntity<ResponseStructure<AddressResponse>> addBloodbankAddress(@RequestBody AddressRequest addressRequest, @PathVariable int bloodbankId){
        AddressResponse addressResponse = addressService.addBloodbankAddress(addressRequest, bloodbankId);
        return responseBuilder.success(HttpStatus.CREATED, "Blood Bank Address Created", addressResponse);
    }

    @PutMapping("/address-user/{addressId}")
    public ResponseEntity<ResponseStructure<AddressResponse>> updateUserAddress(
            @RequestBody AddressRequest addressRequest,
            @PathVariable int addressId) {
        AddressResponse addressResponse = addressService.updateUserAddress(addressRequest, addressId);
        return responseBuilder.success(HttpStatus.OK, "User Address Updated", addressResponse);
    }

    @PreAuthorize("hasAnyAuthority('OWNER_ADMIN', 'GUEST_ADMIN')")
    @PutMapping("/address-hospital/{hospitalId}")
    public ResponseEntity<ResponseStructure<AddressResponse>> updateHospitalAddress(
            @RequestBody AddressRequest addressRequest,
            @PathVariable int hospitalId) {
        AddressResponse addressResponse = addressService.updateHospitalAddress(addressRequest, hospitalId);
        return responseBuilder.success(HttpStatus.OK, "Hospital Address Updated", addressResponse);
    }

    @PreAuthorize("hasAnyAuthority('OWNER_ADMIN', 'GUEST_ADMIN')")
    @PutMapping("/address-bloodbank/{bloodbankId}")
    public ResponseEntity<ResponseStructure<AddressResponse>> updateBloodBankAddress(
            @RequestBody AddressRequest addressRequest,
            @PathVariable int bloodbankId) {
        AddressResponse addressResponse = addressService.updateBloodBankAddress(addressRequest, bloodbankId);
        return responseBuilder.success(HttpStatus.OK, "Blood Bank Address Updated", addressResponse);
    }

    @GetMapping("/address-user")
    public ResponseEntity<ResponseStructure<AddressResponse>> findUserAddress() {
        AddressResponse addressResponse = addressService.findUserAddress();
        return responseBuilder.success(HttpStatus.OK, "User Address Retrieved", addressResponse);
    }

    @PreAuthorize("hasAnyAuthority('OWNER_ADMIN', 'GUEST_ADMIN')")
    @GetMapping("/address-hospital/{hospitalId}")
    public ResponseEntity<ResponseStructure<AddressResponse>> findHospitalAddress(@PathVariable int hospitalId) {
        AddressResponse addressResponse = addressService.findHospitalAddress(hospitalId);
        return responseBuilder.success(HttpStatus.OK, "Hospital Address Retrieved", addressResponse);
    }

    @PreAuthorize("hasAnyAuthority('OWNER_ADMIN', 'GUEST_ADMIN')")
    @GetMapping("/address-bloodbank/{bloodbankId}")
    public ResponseEntity<ResponseStructure<AddressResponse>> findBloodBankAddress(@PathVariable int bloodbankId) {
        AddressResponse addressResponse = addressService.findBloodBankAddress(bloodbankId);
        return responseBuilder.success(HttpStatus.OK, "Blood Bank Address Retrieved", addressResponse);
    }

}
