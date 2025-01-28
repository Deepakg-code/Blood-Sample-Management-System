package com.jsp.bsm.entity;

import com.jsp.bsm.enums.Role;
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
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;


    @OneToOne
    private User user;

    @ManyToOne
    private BloodBank bloodBank;

    @ManyToOne
    private Hospital hospital;
}
