package com.jsp.bsm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    private String addressLine;
    private String landmark;
    private String area;
    private String city;
    private String state;
    private String country;
    private int pincode;

    @OneToOne(mappedBy = "address",fetch = FetchType.EAGER)
    private User user;

    @OneToOne(mappedBy = "address", fetch = FetchType.EAGER)
    private Hospital hospital;

    @OneToOne(mappedBy = "address", fetch = FetchType.EAGER)
    private BloodBank bloodBank;

}
