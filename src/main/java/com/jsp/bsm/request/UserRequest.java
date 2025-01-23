package com.jsp.bsm.request;

import com.jsp.bsm.enums.BloodGroup;
import com.jsp.bsm.enums.Gender;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "username cannot be null")
    @NotBlank(message = "username cannot be blank")
    @Pattern(regexp =  "^[a-zA-Z_][a-zA-Z_]{2,14}$", message = "Username can only contain alphabetic characters (a-z, A-Z) and underscores (_). Please avoid using numbers, spaces, or special characters")
    private String username;

    @NotNull(message = "email cannot be null")
    @NotBlank(message = "email cannot be blank")
    @Email(message = "Invalid Email")
    private String email;

    @NotNull(message = "password cannot be null")
    @NotBlank(message = "password cannot be blank")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%&_`])[A-Za-z\\d!@#$%&_`]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, one special character (!@#$%&_`), and be at least 8 characters long.")
    private String password;

    @NotNull(message = "phone number cannot be null")
    @NotBlank(message = "phone number cannot be blank")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must contain exactly 10 digits and should not include any spaces, letters, or special characters.")
    private String phoneNumber;

    private BloodGroup bloodGroup;

    @Min(value = 2)
    @Max(value = 100)
    private int age;

    private Gender gender;

    @NotNull(message = "city cannot be null")
    @NotBlank(message = "city cannot be blank")
    private String availableCity;
}
