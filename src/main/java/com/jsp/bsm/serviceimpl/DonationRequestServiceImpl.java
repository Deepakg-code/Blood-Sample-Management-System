package com.jsp.bsm.serviceimpl;

import com.jsp.bsm.entity.Admin;
import com.jsp.bsm.entity.BloodBank;
import com.jsp.bsm.entity.DonationRequest;
import com.jsp.bsm.entity.Hospital;
import com.jsp.bsm.enums.OrganizationType;
import com.jsp.bsm.exception.BloodBankNotFoundExceptionById;
import com.jsp.bsm.exception.HospitalNotFoundException;
import com.jsp.bsm.repository.BloodRepository;
import com.jsp.bsm.repository.DonationRequestRepository;
import com.jsp.bsm.repository.HospitalRepository;
import com.jsp.bsm.requestdto.DonationRequestDTO;
import com.jsp.bsm.responsedto.DonationRequestResponse;
import com.jsp.bsm.security.AuthUtil;
import com.jsp.bsm.service.DonationRequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DonationRequestServiceImpl implements DonationRequestService {

    private final DonationRequestRepository donationRequestRepository;
    private final AuthUtil authUtil;
    private final HospitalRepository hospitalRepository;
    private final BloodRepository bloodRepository;

    private DonationRequestResponse mapToDonationRequestResponse(DonationRequest donationRequest) {
        return DonationRequestResponse.builder()
                .requestId(donationRequest.getRequestId())
                .date(donationRequest.getDate())
                .time(donationRequest.getTime())
                .bloodGroup(donationRequest.getBloodGroup())
                .cities(donationRequest.getCities())
                .build();
    }

    private DonationRequest mapToDonationRequest(DonationRequestDTO donationRequestDTO, DonationRequest donationRequest) {
        donationRequest.setBloodGroup(donationRequestDTO.getBloodGroup());
        donationRequest.setCities(donationRequestDTO.getCities());
        return donationRequest;
    }

    @Override
    public DonationRequestResponse addDonationRequestByHospital(DonationRequestDTO donationRequestDTO, int hospitalId) throws Exception {
        DonationRequest donationRequest = mapToDonationRequest(donationRequestDTO, new DonationRequest());
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalNotFoundException("Fail to find hospital"));

        donationRequest.setOrganizationType(OrganizationType.HOSPITAL);
        donationRequest.setHospital(hospital);
        donationRequest.setRequestCompleted(false);

        DonationRequest savedRequest = donationRequestRepository.save(donationRequest);
        hospital.getDonationRequestList().add(savedRequest);
        hospitalRepository.save(hospital);

        return mapToDonationRequestResponse(savedRequest);
    }

    @Override
    public DonationRequestResponse addDonationRequestByBloodBank(DonationRequestDTO donationRequestDTO, int bloodBankId) throws Exception {
        DonationRequest donationRequest = this.mapToDonationRequest(donationRequestDTO, new DonationRequest());
        BloodBank bloodBank = bloodRepository.findById(bloodBankId)
                .orElseThrow(() -> new BloodBankNotFoundExceptionById("Failed to find the blood bank"));

        donationRequest.setOrganizationType(OrganizationType.BLOODBANK);
        donationRequest.setBloodBank(bloodBank);
        donationRequest.setRequestCompleted(false);

        DonationRequest savedRequest = donationRequestRepository.save(donationRequest);
        bloodBank.getDonationRequestList().add(savedRequest);
        bloodRepository.save(bloodBank);

        return mapToDonationRequestResponse(savedRequest);
    }
}
