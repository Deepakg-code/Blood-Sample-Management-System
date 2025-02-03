package com.jsp.bsm.serviceimpl;

import com.jsp.bsm.entity.Admin;
import com.jsp.bsm.entity.BloodBank;
import com.jsp.bsm.entity.DonationRequest;
import com.jsp.bsm.entity.Hospital;
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
                .build();
    }

    private DonationRequest mapToDonationRequest(DonationRequestDTO donationRequestDTO, DonationRequest donationRequest) {
        donationRequest.setDate(donationRequestDTO.getDate());
        donationRequest.setTime(donationRequestDTO.getTime());
        donationRequest.setBloodGroup(donationRequestDTO.getBloodGroup());
        return donationRequest;
    }

    @Override
    public DonationRequestResponse addDonationRequestByHospital(DonationRequestDTO donationRequestDTO, int hospitalId) throws Exception {
        Admin admin = authUtil.getCurrentAdmin();
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(()-> new HospitalNotFoundException("failed to find the hospital"));
        DonationRequest donationRequest = this.mapToDonationRequest(donationRequestDTO, new DonationRequest());
        DonationRequest savedRequest = donationRequestRepository.save(donationRequest);
        List<DonationRequest> hospitalRequests = hospital.getDonationRequests();
        if (hospitalRequests == null) {
            hospitalRequests = new ArrayList<>();
        }
        hospitalRequests.add(savedRequest);
        hospital.setDonationRequests(hospitalRequests);
        hospitalRepository.save(hospital);

        return mapToDonationRequestResponse(savedRequest);
    }

    @Override
    public DonationRequestResponse addDonationRequestByBloodBank(DonationRequestDTO donationRequestDTO, int bloodBankId) throws Exception {
        Admin admin = authUtil.getCurrentAdmin();
        BloodBank bloodBank = bloodRepository.findById(bloodBankId)
                .orElseThrow(()-> new BloodBankNotFoundExceptionById("failed to find the blood bank"));
        DonationRequest donationRequest = this.mapToDonationRequest(donationRequestDTO, new DonationRequest());
        DonationRequest savedRequest = donationRequestRepository.save(donationRequest);
        List<DonationRequest> bloodBankRequests = bloodBank.getDonationRequests();
        if (bloodBankRequests == null) {
            bloodBankRequests = new ArrayList<>();
        }
        bloodBankRequests.add(savedRequest);
        bloodBank.setDonationRequests(bloodBankRequests);
        bloodRepository.save(bloodBank);

        return mapToDonationRequestResponse(savedRequest);
    }
}
