package com.jsp.bsm.serviceimpl;

import com.jsp.bsm.entity.Address;
import com.jsp.bsm.entity.Admin;
import com.jsp.bsm.entity.BloodBank;
import com.jsp.bsm.entity.Sample;
import com.jsp.bsm.enums.BloodGroup;
import com.jsp.bsm.exception.BloodBankNotFoundExceptionById;
import com.jsp.bsm.exception.UserNotFoundExceptionById;
import com.jsp.bsm.mapper.AddressResponseMapper;
import com.jsp.bsm.mapper.SampleResponseMapper;
import com.jsp.bsm.repository.AdminRepository;
import com.jsp.bsm.repository.BloodRepository;
import com.jsp.bsm.repository.SampleRepository;
import com.jsp.bsm.requestdto.BloodBankRequest;
import com.jsp.bsm.responsedto.AddressResponse;
import com.jsp.bsm.responsedto.BloodBankPageResponse;
import com.jsp.bsm.responsedto.BloodBankResponse;
import com.jsp.bsm.responsedto.SampleResponse;
import com.jsp.bsm.service.BloodBankService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BloodBankServiceImpl implements BloodBankService {

    private final BloodRepository bloodRepository;
    private final AdminRepository adminRepository;
    private final SampleRepository sampleRepository;

    private BloodBankResponse mapToBloodBankResponse(BloodBank bloodBank) {
        return BloodBankResponse.builder()
                .bankId(bloodBank.getBankId())
                .name(bloodBank.getName())
                .emergencyUnitCount(bloodBank.getEmergencyUnitCount())
                .build();
    }

    private BloodBank mapToBloodBank(BloodBankRequest bankRequest, BloodBank bloodBank) {
        bloodBank.setName(bankRequest.getName());
        bloodBank.setEmergencyUnitCount(bankRequest.getEmergencyUnitCount());
        return bloodBank;
    }

    @Override
    public BloodBankResponse findBloodBankById(int bankId) {
        BloodBank bloodBank = bloodRepository.findById(bankId)
                .orElseThrow(()->new BloodBankNotFoundExceptionById("Blood Bank Not Found"));
        return mapToBloodBankResponse(bloodBank);
    }

    @Override
    public Page<BloodBankPageResponse> findAllBloodBankByCity(
            List<String> city,
            List<BloodGroup> bloodGroups,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
        Page<BloodBank> bloodBanks = bloodRepository.findByAddress_CityInAndSamples_BloodGroupIn(
                city,
                bloodGroups,
                pageable
        );

        if (bloodBanks.isEmpty()) {
            throw new BloodBankNotFoundExceptionById("No blood banks found in the provided cities and blood groups");
        }

        return bloodBanks.map(bloodBank -> mapToBloodBankPageResponse(bloodBank, bloodGroups));
    }

    private BloodBankPageResponse mapToBloodBankPageResponse(BloodBank bloodBank, List<BloodGroup> requestedBloodGroups) {
        return BloodBankPageResponse.builder()
                .bankId(bloodBank.getBankId())
                .name(bloodBank.getName())
                .address(AddressResponseMapper.mapToAddressResponse(bloodBank.getAddress()))
                .samples(getSampleCollection(bloodBank, requestedBloodGroups))
                .build();
    }

    private static List<SampleResponse> getSampleCollection(BloodBank bloodBank, List<BloodGroup> requestedBloodGroups) {
        return bloodBank.getSamples().stream()
                .filter(sample -> requestedBloodGroups.contains(sample.getBloodGroup()))
                .map(SampleResponseMapper::mapToSampleResponse)
                .collect(Collectors.toList());
    }


    @Override
    public BloodBankResponse updateBloodBankById(int bankId, BloodBankRequest bankRequest) {
        BloodBank exBloodBank = bloodRepository.findById(bankId)
                .orElseThrow(() -> new BloodBankNotFoundExceptionById("Failed to update"));
        BloodBank bloodBank = this.mapToBloodBank(bankRequest, exBloodBank);
        BloodBank updatedBloodbank = bloodRepository.save(bloodBank);
        return  mapToBloodBankResponse(updatedBloodbank);
    }

    @Override
    public BloodBankResponse addAdminBank(BloodBankRequest bankRequest, int adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(()-> new UserNotFoundExceptionById("Admin Not Found"));
        BloodBank bloodBank = mapToBloodBank(bankRequest, new BloodBank());
        bloodRepository.save(bloodBank);

//        List<Admin> admins = new ArrayList<>();
//        admins.add(admin);
//        bloodBank.setAdmin(admins);

        admin.setBloodBank(bloodBank);
        adminRepository.save(admin);
        return this.mapToBloodBankResponse(bloodBank);
    }


}
