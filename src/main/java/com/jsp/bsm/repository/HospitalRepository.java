package com.jsp.bsm.repository;

import com.jsp.bsm.entity.DonationRequest;
import com.jsp.bsm.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

    Hospital findByDonationRequestList(DonationRequest donationRequest);

}
