package com.jsp.bsm.repository;

import com.jsp.bsm.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SampleRepository extends JpaRepository<Sample, Integer> {
}
