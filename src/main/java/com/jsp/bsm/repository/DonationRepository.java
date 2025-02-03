package com.jsp.bsm.repository;

import com.jsp.bsm.entity.Donation;
import com.jsp.bsm.entity.DonationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Integer> {

    List<Donation> findByDonationRequestList(DonationRequest donationRequest);
}
