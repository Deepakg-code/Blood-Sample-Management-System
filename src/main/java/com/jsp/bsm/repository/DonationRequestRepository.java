package com.jsp.bsm.repository;

import com.jsp.bsm.entity.DonationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRequestRepository extends JpaRepository<DonationRequest, Integer> {
}
