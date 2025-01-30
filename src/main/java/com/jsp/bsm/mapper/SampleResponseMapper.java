package com.jsp.bsm.mapper;

import com.jsp.bsm.entity.Sample;
import com.jsp.bsm.responsedto.SampleResponse;

public class SampleResponseMapper {

    public static SampleResponse mapToSampleResponse(Sample sample) {
        return SampleResponse.builder()
                .sampleId(sample.getSampleId())
                .bloodGroup(sample.getBloodGroup())
                .quantity(sample.getQuantity())
                .availability(sample.isAvailability())
                .emergencyUnits(sample.getEmergencyUnits())
                .availableUnits(sample.getAvailableUnits())
                .build();
    }

}
