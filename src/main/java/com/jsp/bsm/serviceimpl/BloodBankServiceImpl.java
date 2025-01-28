package com.jsp.bsm.serviceimpl;

import com.jsp.bsm.entity.Address;
import com.jsp.bsm.entity.Admin;
import com.jsp.bsm.entity.BloodBank;
import com.jsp.bsm.enums.Role;
import com.jsp.bsm.exception.BloodBankNotFoundExceptionById;
import com.jsp.bsm.exception.UserNotFoundExceptionById;
import com.jsp.bsm.repository.AdminRepository;
import com.jsp.bsm.repository.BloodRepository;
import com.jsp.bsm.requestdto.BloodBankRequest;
import com.jsp.bsm.responsedto.BloodBankResponse;
import com.jsp.bsm.service.BloodBankService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BloodBankServiceImpl implements BloodBankService {

    private final BloodRepository bloodRepository;
    private final AdminRepository adminRepository;

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
    public BloodBankResponse addBloodBank(BloodBankRequest bankRequest) {
        BloodBank bloodBank = mapToBloodBank(bankRequest, new BloodBank());
        bloodBank = bloodRepository.save(bloodBank);
        return mapToBloodBankResponse(bloodBank);
    }

    @Override
    public BloodBankResponse findBloodBankById(int bankId) {
        BloodBank bloodBank = bloodRepository.findById(bankId)
                .orElseThrow(()->new BloodBankNotFoundExceptionById("Blood Bank Not Found"));
        return mapToBloodBankResponse(bloodBank);
    }

    @Override
    public List<BloodBankResponse> findAllBloodBankByCity(List<String> city) {
        List<BloodBank> bloodBanks = bloodRepository.findByAddress_CityIn(city);
        if (bloodBanks.isEmpty()) {
            throw new RuntimeException("No blood banks found in the provided cities");
        }
        return bloodBanks.stream()
                .map(this::mapToBloodBankResponse) // Mapping each BloodBank to BloodBankResponse
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
        List<Admin> admins = new ArrayList<>();
        admins.add(admin);
        BloodBank bloodBank = BloodBank.builder()
                .admin(admins)
                .name(bankRequest.getName())
                .emergencyUnitCount(bankRequest.getEmergencyUnitCount())
                .build();
        bloodRepository.save(bloodBank);
        return this.mapToBloodBankResponse(bloodBank);
    }


}
