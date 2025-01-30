package com.jsp.bsm.service;

import com.jsp.bsm.requestdto.AddressRequest;
import com.jsp.bsm.responsedto.AddressResponse;

public interface AddressService {
    AddressResponse addUserAddress(AddressRequest addressRequest);

    AddressResponse addHospitalAddress(AddressRequest addressRequest, int hospitalId);

    AddressResponse addBloodbankAddress(AddressRequest addressRequest, int bloodbankId);

    AddressResponse updateUserAddress(AddressRequest addressRequest, int addressId);

    AddressResponse updateHospitalAddress(AddressRequest addressRequest, int hospitalId);

    AddressResponse updateBloodBankAddress(AddressRequest addressRequest, int bloodbankId);

    AddressResponse findUserAddress();

    AddressResponse findHospitalAddress(int hospitalId);

    AddressResponse findBloodBankAddress(int bloodbankId);


}
