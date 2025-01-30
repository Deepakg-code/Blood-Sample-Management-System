package com.jsp.bsm.exceptionhandler;

import com.jsp.bsm.exception.SampleNotFoundException;
import com.jsp.bsm.exception.SurveyNotFoundException;
import com.jsp.bsm.utility.ErrorStructure;
import com.jsp.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class SurveyExceptionHandler {

    private final RestResponseBuilder responseBuilder;

    @ExceptionHandler(SurveyNotFoundException.class)
    public<T> ResponseEntity<ErrorStructure<String>> handleSurveyNotFoundById(SampleNotFoundException ex){
        return responseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "Survey Not Found By given Id");
    }
}
