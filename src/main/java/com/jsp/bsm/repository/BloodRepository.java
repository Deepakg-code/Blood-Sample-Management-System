package com.jsp.bsm.repository;

import com.jsp.bsm.entity.BloodBank;
import com.jsp.bsm.entity.DonationRequest;
import com.jsp.bsm.enums.BloodGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BloodRepository extends JpaRepository<BloodBank, Integer> {

    Page<BloodBank> findByAddress_CityInAndSamples_BloodGroupIn(List<String> cities, List<BloodGroup> bloodGroups, Pageable pageable);

    Optional<BloodBank> findByDonationRequestList(DonationRequest donationRequest);

}
