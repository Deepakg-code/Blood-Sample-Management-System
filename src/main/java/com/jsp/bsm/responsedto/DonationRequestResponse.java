package com.jsp.bsm.responsedto;

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
public class DonationRequestResponse {

    private int requestId;
    private LocalDate date;
    private LocalTime time;
    private List<BloodGroup> bloodGroup;
    private List<String> cities;

}
