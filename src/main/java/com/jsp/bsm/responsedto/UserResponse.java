package com.jsp.bsm.responsedto;

import com.jsp.bsm.enums.BloodGroup;
import com.jsp.bsm.enums.Gender;
import com.jsp.bsm.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private int userId;
    private String username;
    private BloodGroup bloodGroup;
    private LocalDate lastDonatedAt;
    private int age;
    private Gender gender;
    private String availableCity;
    private boolean verified;
    private Role role;


}
