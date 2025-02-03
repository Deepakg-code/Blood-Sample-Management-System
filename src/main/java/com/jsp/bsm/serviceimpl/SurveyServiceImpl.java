package com.jsp.bsm.serviceimpl;

import com.jsp.bsm.entity.Survey;
import com.jsp.bsm.entity.User;
import com.jsp.bsm.exception.SurveyNotFoundException;
import com.jsp.bsm.repository.SurveyRepository;
import com.jsp.bsm.requestdto.SurveyRequest;
import com.jsp.bsm.requestdto.UserRequest;
import com.jsp.bsm.responsedto.SurveyResponse;
import com.jsp.bsm.responsedto.UserResponse;
import com.jsp.bsm.security.AuthUtil;
import com.jsp.bsm.service.SurveyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    private final AuthUtil authUtil;

    private Survey mapToSurvey(SurveyRequest surveyRequest, Survey survey) {
        survey.setPreMedicalCondition((surveyRequest.isPreMedicalCondition()));
        survey.setConsumedAlcohol(surveyRequest.isConsumedAlcohol());
        survey.setConsumedTobacco(surveyRequest.isConsumedTobacco());
        survey.setAdditives(surveyRequest.isAdditives());
        survey.setMedicines(surveyRequest.isMedicines());
        return survey;
    }

    private SurveyResponse mapToSurveyResponse(Survey survey) {
        return SurveyResponse.builder()
                .surveyId(survey.getSurveyId())
                .preMedicalCondition(survey.isPreMedicalCondition())
                .consumedAlcohol(survey.isConsumedAlcohol())
                .consumedTobacco(survey.isConsumedTobacco())
                .additives(survey.isAdditives())
                .medicines(survey.isMedicines())
                .build();
    }

    @Override
    public SurveyResponse addSurvey(SurveyRequest surveyRequest) {
        User user = authUtil.getCurrentUser();
        Survey survey = this.mapToSurvey(surveyRequest, new Survey());
        survey.setUser(user);
        survey = surveyRepository.save(survey);
        return this.mapToSurveyResponse(survey);
    }

    @Override
    public SurveyResponse findSurvey(int surveyId) {
        User user = authUtil.getCurrentUser();
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(()-> new SurveyNotFoundException("Survey not found"));
        return this.mapToSurveyResponse(survey);
    }

    @Override
    public SurveyResponse updateSurveyById(SurveyRequest surveyRequest, int surveyId) {
        User user = authUtil.getCurrentUser();

        Survey exSurvey = surveyRepository.findById(surveyId)
                .orElseThrow(()-> new SurveyNotFoundException("Failed to find the survey"));
        Survey survey = this.mapToSurvey(surveyRequest, exSurvey);
        Survey updatedSurvey = surveyRepository.save(survey);
        return mapToSurveyResponse(updatedSurvey);

    }

}
