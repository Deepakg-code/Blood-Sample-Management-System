package com.jsp.bsm.serviceimpl;

import com.jsp.bsm.entity.Address;
import com.jsp.bsm.entity.BloodBank;
import com.jsp.bsm.entity.Hospital;
import com.jsp.bsm.entity.User;
import com.jsp.bsm.exception.BloodBankNotFoundExceptionById;
import com.jsp.bsm.exception.HospitalNotFoundException;
import com.jsp.bsm.repository.AddressRepository;
import com.jsp.bsm.repository.BloodRepository;
import com.jsp.bsm.repository.HospitalRepository;
import com.jsp.bsm.repository.UserRepository;
import com.jsp.bsm.requestdto.AddressRequest;
import com.jsp.bsm.responsedto.AddressResponse;
import com.jsp.bsm.security.AuthUtil;
import com.jsp.bsm.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final HospitalRepository hospitalRepository;
    private final BloodRepository bloodRepository;
    private final AuthUtil authUtil;

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

    private Address mapToAddress(AddressRequest addressRequest, Address address) {
                address.setAddressLine(addressRequest.getAddressLine());
                address.setLandmark(addressRequest.getLandmark());
                address.setArea(addressRequest.getArea());
                address.setCity(addressRequest.getCity());
                address.setState(addressRequest.getState());
                address.setCountry(addressRequest.getCountry());
                address.setPincode(addressRequest.getPincode());
                return address;
    }
    @Override
    public AddressResponse addUserAddress(AddressRequest addressRequest) {
        User user = authUtil.getCurrentUser();
        Address address = this.mapToAddress(addressRequest, new Address());
        address = addressRepository.save(address);

        user.setAddress(address);
        userRepository.save(user);

        return this.mapToAddressResponse(address);
    }

    @Override
    public AddressResponse addHospitalAddress(AddressRequest addressRequest, int hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(()-> new HospitalNotFoundException("Hospital not found by given id"));
        Address address = this.mapToAddress(addressRequest, new Address());
        address = addressRepository.save(address);
        hospital.setAddress(address);
        hospitalRepository.save(hospital);
        return mapToAddressResponse(address);
    }

    @Override
    public AddressResponse addBloodbankAddress(AddressRequest addressRequest, int bloodbankId) {
        BloodBank bloodBank = bloodRepository.findById(bloodbankId)
                .orElseThrow(()-> new BloodBankNotFoundExceptionById("Blood Bank not found by given id"));
        Address address = this.mapToAddress(addressRequest, new Address());
        address = addressRepository.save(address);
        bloodBank.setAddress(address);
        bloodRepository.save(bloodBank);
        return mapToAddressResponse(address);
    }

    @Override
    public AddressResponse updateUserAddress(AddressRequest addressRequest, int addressId) {
        User user = authUtil.getCurrentUser();
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found with ID: " + addressId));

        if (!user.getAddress().equals(address)) {
            throw new RuntimeException("You are not authorized to update this address");
        }

        address = this.mapToAddress(addressRequest, address);
        address = addressRepository.save(address);

        return this.mapToAddressResponse(address);
    }


    @Override
    public AddressResponse updateHospitalAddress(AddressRequest addressRequest, int hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found by given ID"));

        Address address = hospital.getAddress();
        address = this.mapToAddress(addressRequest, address);
        address = addressRepository.save(address);

        return this.mapToAddressResponse(address);
    }


    @Override
    public AddressResponse updateBloodBankAddress(AddressRequest addressRequest, int bloodbankId) {
        BloodBank bloodBank = bloodRepository.findById(bloodbankId)
                .orElseThrow(() -> new BloodBankNotFoundExceptionById("Blood Bank not found by given ID"));

        Address address = bloodBank.getAddress();
        address = this.mapToAddress(addressRequest, address);
        address = addressRepository.save(address);

        return this.mapToAddressResponse(address);
    }

    @Override
    public AddressResponse findUserAddress() {
        User user = authUtil.getCurrentUser();
        Address address = user.getAddress();
        if (address == null) {
            throw new RuntimeException("Address not found for the logged-in user");
        }
        return mapToAddressResponse(address);
    }

    @Override
    public AddressResponse findHospitalAddress(int hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found by given ID"));
        Address address = hospital.getAddress();
        if (address == null) {
            throw new RuntimeException("Address not found for the hospital with ID: " + hospitalId);
        }
        return mapToAddressResponse(address);
    }

    @Override
    public AddressResponse findBloodBankAddress(int bloodbankId) {
        BloodBank bloodBank = bloodRepository.findById(bloodbankId)
                .orElseThrow(() -> new BloodBankNotFoundExceptionById("Blood Bank not found by given ID"));
        Address address = bloodBank.getAddress();
        if (address == null) {
            throw new RuntimeException("Address not found for the blood bank with ID: " + bloodbankId);
        }
        return mapToAddressResponse(address);
    }




}
