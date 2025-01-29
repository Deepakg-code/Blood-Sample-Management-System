package com.jsp.bsm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BloodBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bankId;

    private String name;
    private int emergencyUnitCount;

    @OneToMany(mappedBy = "bloodBank")
    private List<Admin> admin;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "bloodBank", fetch = FetchType.EAGER)
    private List<Sample> samples;

}
