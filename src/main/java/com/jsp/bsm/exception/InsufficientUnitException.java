package com.jsp.bsm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InsufficientUnitException extends RuntimeException {
    private final String message;
}
