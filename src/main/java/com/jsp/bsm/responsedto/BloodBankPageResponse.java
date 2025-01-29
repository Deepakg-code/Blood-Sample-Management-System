package com.jsp.bsm.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BloodBankPageResponse {

    private int bankId;
    private String name;

    AddressResponse address;

    List<SampleResponse> samples;
}
