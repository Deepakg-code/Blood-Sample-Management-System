package com.jsp.bsm.mapper;

import com.jsp.bsm.entity.Address;
import com.jsp.bsm.responsedto.AddressResponse;

public class AddressResponseMapper {

    public static AddressResponse mapToAddressResponse(Address address) {
        return AddressResponse.builder()
                .addressId(address.getAddressId())
                .addressLine(address.getAddressLine())
                .landmark(address.getLandmark())
                .area(address.getArea())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .pincode(address.getPincode())
                .build();
    }

}
