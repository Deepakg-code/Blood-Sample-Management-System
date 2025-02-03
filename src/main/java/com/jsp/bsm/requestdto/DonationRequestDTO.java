package com.jsp.bsm.requestdto;

import com.jsp.bsm.enums.BloodGroup;
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

    private LocalDate date;
    private LocalTime time;
    private List<BloodGroup> bloodGroup;
}
