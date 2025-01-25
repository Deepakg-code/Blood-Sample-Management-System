package com.jsp.bsm.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jsp.bsm.enums.AdminType;
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
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;


    @OneToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private AdminType adminType;

}
