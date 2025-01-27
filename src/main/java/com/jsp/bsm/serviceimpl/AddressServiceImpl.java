package com.jsp.bsm.serviceimpl;

import com.jsp.bsm.entity.Address;
import com.jsp.bsm.repository.AddressRepository;
import com.jsp.bsm.requestdto.AddressRequest;
import com.jsp.bsm.responsedto.AddressResponse;
import com.jsp.bsm.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private AddressResponse mapToAddressResponse(Address address) {
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

    private Address mapToAddress(AddressRequest addressRequest) {
        return Address.builder()
                .addressLine(addressRequest.getAddressLine())
                .landmark(addressRequest.getLandmark())
                .area(addressRequest.getArea())
                .city(addressRequest.getCity())
                .state(addressRequest.getState())
                .country(addressRequest.getCountry())
                .pincode(addressRequest.getPincode())
                .build();
    }
    @Override
    public AddressResponse addAddress(AddressRequest addressRequest) {
        Address address = this.mapToAddress(addressRequest);
        address = addressRepository.save(address);
        return this.mapToAddressResponse(address);
    }

}
