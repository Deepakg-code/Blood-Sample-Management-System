package com.jsp.bsm.service;

import com.jsp.bsm.requestdto.SampleRequest;
import com.jsp.bsm.responsedto.SampleResponse;

import java.util.List;

public interface SampleService {
    SampleResponse addSample(SampleRequest sampleRequest);

    SampleResponse findSampleById(int sampleId);

    List<SampleResponse> findAllSamples();

    SampleResponse updateSampleId(int sampleId, SampleRequest sampleRequest);

    SampleResponse addSampleToBank(SampleRequest sampleRequest, int bankId);
}
