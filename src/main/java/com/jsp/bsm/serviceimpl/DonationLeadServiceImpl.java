package com.jsp.bsm.serviceimpl;

import com.jsp.bsm.entity.DonationLead;
import com.jsp.bsm.entity.DonationRequest;
import com.jsp.bsm.entity.User;
import com.jsp.bsm.repository.DonationLeadRepository;
import com.jsp.bsm.repository.DonationRequestRepository;
import com.jsp.bsm.repository.UserRepository;
import com.jsp.bsm.requestdto.DonationLeadRequest;
import com.jsp.bsm.responsedto.DonationLeadResponse;
import com.jsp.bsm.security.AuthUtil;
import com.jsp.bsm.service.DonationLeadService;
import lombok.AllArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DonationLeadServiceImpl implements DonationLeadService {

    private final DonationLeadRepository donationLeadRepository;
    private final DonationRequestRepository donationRequestRepository;
    private final UserRepository userRepository;
    private final AuthUtil authUtil;


    @Override
    public DonationLeadResponse addDonationLead(DonationLeadRequest donationLeadRequest, int userId, int requestId) {
        User user = authUtil.getCurrentUser();

        DonationRequest donationRequest = donationRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Donation Request Not Found"));

        DonationLead donationLead = new DonationLead();
        donationLead.setDate(donationLeadRequest.getDate());
        donationLead.setTime(donationLeadRequest.getTime());
        donationLead.setUser(user);

        if (donationLead.getDonationRequests() == null) {
            donationLead.setDonationRequests(new ArrayList<>());
        }

        donationRequest.setDonationLead(donationLead);
        donationLead.getDonationRequests().add(donationRequest);

        DonationLead savedDonationLead = donationLeadRepository.save(donationLead);

        return DonationLeadResponse.builder()
                .leadId(savedDonationLead.getLeadId())
                .date(savedDonationLead.getDate())
                .time(savedDonationLead.getTime())
                .build();
    }

    @Override
    public DonationLeadResponse findDonationLeadById(int donationId) {
        Optional<DonationLead> donationLeadOptional = donationLeadRepository.findById(donationId);

        if (donationLeadOptional.isPresent()) {
            DonationLead donationLead = donationLeadOptional.get();
            return DonationLeadResponse.builder()
                    .leadId(donationLead.getLeadId())
                    .date(donationLead.getDate())
                    .time(donationLead.getTime())
                    .build();
        } else {
            throw new RuntimeException("Donation Lead not found with ID: " + donationId);
        }
    }
}
