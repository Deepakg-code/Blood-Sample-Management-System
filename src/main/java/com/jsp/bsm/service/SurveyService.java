package com.jsp.bsm.service;

import com.jsp.bsm.requestdto.SurveyRequest;
import com.jsp.bsm.responsedto.SurveyResponse;

public interface SurveyService {

    SurveyResponse addSurvey(SurveyRequest surveyRequest);

    SurveyResponse findSurvey(int surveyId);

    SurveyResponse updateSurveyById(SurveyRequest surveyRequest, int surveyId);
}
