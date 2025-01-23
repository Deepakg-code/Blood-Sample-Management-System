package com.jsp.bsm.request;

import com.jsp.bsm.enums.BloodGroup;
import com.jsp.bsm.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private BloodGroup bloodGroup;
    private int age;
    private Gender gender;
    private String availableCity;
}
