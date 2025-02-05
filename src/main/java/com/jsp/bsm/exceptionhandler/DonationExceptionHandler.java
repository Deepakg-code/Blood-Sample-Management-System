package com.jsp.bsm.exceptionhandler;

import com.jsp.bsm.exception.DonationNotFoundException;
import com.jsp.bsm.exception.SampleNotFoundException;
import com.jsp.bsm.utility.ErrorStructure;
import com.jsp.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class DonationExceptionHandler {

    private final RestResponseBuilder responseBuilder;

    @ExceptionHandler(DonationNotFoundException.class)
    public<T> ResponseEntity<ErrorStructure<String>> handleDonationNotFound(DonationNotFoundException ex){
        return responseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "Donation Not Found By given Id");
    }
}
