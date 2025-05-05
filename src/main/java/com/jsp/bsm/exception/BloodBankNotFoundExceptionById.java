package com.jsp.bsm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BloodBankNotFoundByIdException extends Exception {
    private final String message;
}
