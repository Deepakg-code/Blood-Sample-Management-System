package com.jsp.bsm.entity;

import com.jsp.bsm.enums.BloodGroup;
import com.jsp.bsm.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    private LocalDate date;
    private LocalTime time;
    private int noOfUnits;
    private TransactionType transactionType;
    private BloodGroup bloodGroup;

    @ManyToOne
    private User user;

    @ManyToOne
    private Hospital hospital;

    @ManyToOne
    private BloodBank bloodBank;
}
