package com.jsp.bsm.mail;

import com.jsp.bsm.entity.*;
import com.jsp.bsm.repository.BloodRepository;
import com.jsp.bsm.repository.DonationRequestRepository;
import com.jsp.bsm.repository.HospitalRepository;
import com.jsp.bsm.repository.UserRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Component
@AllArgsConstructor
public class NotificationWorker {
    private MailService mailService;

    private UserRepository userRepository;

    private HospitalRepository hospitalRepository;

    private BloodRepository bloodBankRepository;


    private DonationRequestRepository donationRequestRepository;


    private void sendBloodDonationRequestNotification(Organization org, DonationRequest donationRequest) throws MessagingException {

        List<User> users = userRepository.findByAvailableCityInAndBloodGroupIn(donationRequest.getCities(), donationRequest.getBloodGroup());
        for (User user : users) {
            Map<String,Object> variables = new HashMap<>();
            variables.put("organizationName",org.getOrganizationName());
            variables.put("organizationAddress",org.getOrganizationAddress());
            variables.put("bloodGroup",user.getBloodGroup().name());
            variables.put("surveyLink","");
            variables.put("donateLink","");
            String text = mailService.generateContent("DonationRequest",variables);
            mailService.sendMail(user.getEmail(), "URGENT !! BLOOD REQUEST", text);

        }
    }

    @Scheduled(fixedRate = 60000)
    public void sendBloodDonationRequestNotification() throws MessagingException { //void because these happen at background and no parameter because it is triggered
        List<DonationRequest> donationRequestList = donationRequestRepository.findByrequestCompleted(false);

        for (DonationRequest donationRequest : donationRequestList) {
            Organization org = this.findOrganizationDetails(donationRequest);
            this.sendBloodDonationRequestNotification(org, donationRequest);
        }
    }


    public Organization findOrganizationDetails(DonationRequest donationRequest) {

        Organization org = new Organization();
        switch (donationRequest.getOrganizationType()) {
            case HOSPITAL -> {
                Hospital hospital = hospitalRepository.findByDonationRequestList(donationRequest);

                if (hospital != null) {
                    org.setOrganizationName(hospital.getName());
                    Address address = hospital.getAddress();
                    org.organizationAddress = getOrganizationAddress(address);
                }
            }
            case BLOODBANK -> {
                Optional<BloodBank> optional = bloodBankRepository.findByDonationRequestList(donationRequest);

                if (optional.isPresent()) {
                    BloodBank bloodBank = optional.get();
                    org.organizationName = bloodBank.getName();
                    Address address = bloodBank.getAddress();
                    org.organizationAddress = getOrganizationAddress(address);
                }
            }
        }
        return org;
    }

    private static String getOrganizationAddress(Address address) {
        return address.getAddressLine() + " \n  " + address.getArea() +
                " \n  " + address.getCity() + " \n  " + address.getState()
                + " \n  " + address.getCountry() + " \n  " + address.getPincode();
    }


    @Getter
    @Setter
    public static class Organization {
        private String organizationName;
        private String organizationAddress;
    }

}