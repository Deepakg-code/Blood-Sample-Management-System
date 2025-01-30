package com.jsp.bsm.exceptionhandler;

import com.jsp.bsm.exception.HospitalNotFoundException;
import com.jsp.bsm.utility.ErrorStructure;
import com.jsp.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class HospitalExceptionHandler {

    private final RestResponseBuilder responseBuilder;

    @ExceptionHandler(HospitalNotFoundException.class)
    public<T> ResponseEntity<ErrorStructure<String>> handleHospitalNotFoundById(HospitalNotFoundException ex){
        return responseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "Hospital Not Found By given Id");
    }
}
