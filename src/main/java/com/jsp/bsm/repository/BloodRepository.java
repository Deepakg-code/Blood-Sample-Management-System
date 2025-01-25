package com.jsp.bsm.repository;

import com.jsp.bsm.entity.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodRepository extends JpaRepository<BloodBank, Integer> {
}
