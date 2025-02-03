package com.jsp.bsm.requestdto;

import com.jsp.bsm.enums.BloodGroup;
import com.jsp.bsm.enums.OrganizationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonationRequestDTO {

    private List<BloodGroup> bloodGroup;
    private List<String> cities;
}
