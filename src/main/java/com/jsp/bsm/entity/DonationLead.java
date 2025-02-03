package com.jsp.bsm.entity;

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
public class DonationLead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leadId;
    private LocalDate date;
    private LocalTime time;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "donationLead")
    private List<DonationRequest> donationRequests;
}
