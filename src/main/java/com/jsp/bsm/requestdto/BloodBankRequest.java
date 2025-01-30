package com.jsp.bsm.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BloodBankRequest {

    @NotNull(message = "hospital name cannot be null")
    @NotBlank(message = "hospital name cannot be blank")
    @Pattern(regexp =  "^[a-zA-Z_][a-zA-Z_]{2,50}$", message = "Hospital name can only contain alphabetic characters (a-z, A-Z) and underscores (_). Please avoid using numbers, spaces, or special characters")
    private String name;
    private int emergencyUnitCount;
}
