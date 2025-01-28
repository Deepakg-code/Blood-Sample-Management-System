package com.jsp.bsm.repository;

import com.jsp.bsm.entity.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BloodRepository extends JpaRepository<BloodBank, Integer> {

    public List<BloodBank> findByAddress_CityIn(List<String> cities);
}
