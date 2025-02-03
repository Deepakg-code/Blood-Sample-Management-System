package com.jsp.bsm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jsp.bsm.enums.BloodGroup;
import com.jsp.bsm.enums.Gender;
import com.jsp.bsm.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private BloodGroup bloodGroup;
    private LocalDate lastDonatedAt;
    private int age;
    private Gender gender;
    private String availableCity;
    private boolean verified;
    private Role role;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    private Admin admin;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transaction;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private  List<Survey> surveys;

    @OneToMany(mappedBy = "user")
    private List<Donation> donations;

    @OneToMany(mappedBy = "user")
    private List<DonationLead> donationLeads;

}
