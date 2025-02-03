package com.jsp.bsm.repository;

import com.jsp.bsm.entity.DonationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRequestRepository extends JpaRepository<DonationRequest, Integer> {

    List<DonationRequest> findByrequestCompleted(boolean value);
}
