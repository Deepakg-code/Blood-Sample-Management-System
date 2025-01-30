package com.jsp.bsm.repository;

import com.jsp.bsm.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}
