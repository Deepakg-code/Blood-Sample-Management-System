package com.jsp.bsm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int userId;
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;
    private enum bloodGroup{
        A_POSITIVE,
        A_NEGATIVE,
        B_POSITIVE,
        B_NEGATIVE,
        O_POSITIVE,
        O_NEGATIVE,
        AB_POSITIVE,
        AB_NEGATIVE
    }
    private LocalDate lastDonatedAt;
    private int age;
    private enum gender{
        MALE,
        FEMALE
    }

    private String availableCity;
    private boolean verified;




}
