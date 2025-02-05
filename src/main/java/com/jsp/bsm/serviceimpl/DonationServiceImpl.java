package com.jsp.bsm.serviceimpl;

import com.jsp.bsm.entity.Donation;
import com.jsp.bsm.entity.DonationRequest;
import com.jsp.bsm.entity.User;
import com.jsp.bsm.exception.DonationNotFoundException;
import com.jsp.bsm.repository.DonationRepository;
import com.jsp.bsm.repository.DonationRequestRepository;
import com.jsp.bsm.requestdto.DonationReq;
import com.jsp.bsm.responsedto.DonationResponse;
import com.jsp.bsm.security.AuthUtil;
import com.jsp.bsm.service.DonationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;
    private final AuthUtil authUtil;

    private DonationRequestRepository  donationRequestRepository;

    private Donation mapToDonation(DonationReq donationReq, Donation donation) {
        donation.setDate(donationReq.getDate());
        donation.setTime(donationReq.getTime());
        return donation;
    }

    private DonationResponse mapToDonationResponse(Donation savedDonation) {
        return DonationResponse.builder()
                .donationId(savedDonation.getDonationId())
                .date(savedDonation.getDate())
                .time(savedDonation.getTime())
                .build();
    }

    @Override
    public DonationResponse addDonation(DonationReq donationReq, int requestId) {
        User user = authUtil.getCurrentUser();
        DonationRequest donationRequest = donationRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Donation Request Not found"));

        Donation donation = this.mapToDonation(donationReq, new Donation());
        donation.setUser(user);

        if (donation.getDonationRequestList() == null) {
            donation.setDonationRequestList(new ArrayList<>());
        }

        donationRequest.setDonation(donation);
        donation.getDonationRequestList().add(donationRequest);

        Donation savedDonation = donationRepository.save(donation);

        return this.mapToDonationResponse(savedDonation);
    }

    @Override
    public DonationResponse updateDonation(DonationReq donationReq, int donationId) {
        User user = authUtil.getCurrentUser();
        Donation existingDonation = donationRepository.findById(donationId)
                .orElseThrow(() -> new DonationNotFoundException("Donation Not Found"));
        if(user.getUserId()==existingDonation.getUser().getUserId()) {
            existingDonation.setDate(donationReq.getDate());
            existingDonation.setTime(donationReq.getTime());

            Donation updatedDonation = donationRepository.save(existingDonation);

            return this.mapToDonationResponse(updatedDonation);
        }
        else{
            throw new DonationNotFoundException("No donation available by this donation id in your account");
        }
    }

    @Override
    public List<DonationResponse> findAllDonationByDonationRequest(int requestId) {
        DonationRequest donationRequest = donationRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Donation request not found"));

        List<Donation> donations = donationRepository.findByDonationRequestList(donationRequest);

        return donations.stream()
                .map(donation -> DonationResponse.builder()
                        .donationId(donation.getDonationId())
                        .date(donation.getDate())
                        .time(donation.getTime())
                        .build())
                .collect(Collectors.toList());
    }
}

