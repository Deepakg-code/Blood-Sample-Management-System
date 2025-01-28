package com.jsp.bsm.repository;

import com.jsp.bsm.entity.BloodBank;
import com.jsp.bsm.enums.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BloodRepository extends JpaRepository<BloodBank, Integer> {

//    public List<BloodBank> findByAddress_CityIn(List<String> cities);
List<BloodBank> findByAddress_CityInAndSamples_BloodGroup(List<String> cities, BloodGroup bloodGroup);

}
