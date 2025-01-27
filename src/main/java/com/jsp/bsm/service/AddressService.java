package com.jsp.bsm.service;

import com.jsp.bsm.requestdto.AddressRequest;
import com.jsp.bsm.responsedto.AddressResponse;

public interface AddressService {
    AddressResponse addAddress(AddressRequest addressRequest);
}
