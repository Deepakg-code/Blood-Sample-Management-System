package com.jsp.bsm.entity;

import com.jsp.bsm.enums.BloodGroup;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonationRequest{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;

    private LocalDate date;
    private LocalTime time;
    private List<BloodGroup> bloodGroup;
}
