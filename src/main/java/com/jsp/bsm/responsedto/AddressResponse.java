package com.jsp.bsm.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    private int addressId;
    private String addressLine;
    private String landmark;
    private String area;
    private String city;
    private String state;
    private String country;
    private int pincode;
}
