package com.jsp.bsm.repository;

import com.jsp.bsm.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Integer> {
}
