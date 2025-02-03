package com.jsp.bsm.controller;

import com.jsp.bsm.entity.Survey;
import com.jsp.bsm.requestdto.BloodBankRequest;
import com.jsp.bsm.requestdto.SurveyRequest;
import com.jsp.bsm.responsedto.BloodBankResponse;
import com.jsp.bsm.responsedto.SurveyResponse;
import com.jsp.bsm.service.SurveyService;
import com.jsp.bsm.utility.ResponseStructure;
import com.jsp.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/surveys")
    public ResponseEntity<ResponseStructure<SurveyResponse>> addSurvey(@RequestBody SurveyRequest surveyRequest){
        SurveyResponse surveyResponse = surveyService.addSurvey(surveyRequest);
        return responseBuilder.success(HttpStatus.CREATED, "Survey Created", surveyResponse);
    }

    @GetMapping("/surveys/{surveyId}")
    public ResponseEntity<ResponseStructure<SurveyResponse>> findSurvey(@PathVariable int surveyId){
        SurveyResponse surveyResponse = surveyService.findSurvey(surveyId);
        return responseBuilder.success(HttpStatus.FOUND, "Survey Found", surveyResponse);
    }

    @PutMapping("/surveys/{surveyId}")
    public ResponseEntity<ResponseStructure<SurveyResponse>> findSurvey(@RequestBody SurveyRequest surveyRequest, @PathVariable int surveyId){
        SurveyResponse surveyResponse = surveyService.updateSurveyById(surveyRequest, surveyId);
        return responseBuilder.success(HttpStatus.FOUND, "Survey Found", surveyResponse);
    }
}
