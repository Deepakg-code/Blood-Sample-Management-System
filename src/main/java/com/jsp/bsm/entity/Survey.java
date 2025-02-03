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
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int surveyId;
    private boolean preMedicalCondition;
    private boolean consumedAlcohol;
    private boolean consumedTobacco;
    private boolean additives;
    private boolean medicines;

    @ManyToOne
    private User user;
}
