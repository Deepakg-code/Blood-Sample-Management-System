package com.jsp.bsm.entity;

import com.jsp.bsm.enums.BloodGroup;
import com.jsp.bsm.enums.OrganizationType;
import jakarta.persistence.*;
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
public class DonationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;

    private LocalDate date;
    private LocalTime time;


    private List<BloodGroup> bloodGroup;

    private List<String> cities;

    @Enumerated(EnumType.STRING)
    private OrganizationType organizationType;

    private boolean requestCompleted;

    @ManyToOne(cascade = CascadeType.ALL)
    private Hospital hospital;

    @ManyToOne(cascade = CascadeType.ALL)
    private BloodBank bloodBank;

    @ManyToOne
    private Donation donation;

    @ManyToOne
    private DonationLead donationLead;
}