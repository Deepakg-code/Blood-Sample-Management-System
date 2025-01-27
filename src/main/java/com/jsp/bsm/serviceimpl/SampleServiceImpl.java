package com.jsp.bsm.serviceimpl;

import com.jsp.bsm.entity.BloodBank;
import com.jsp.bsm.entity.Sample;
import com.jsp.bsm.exception.BloodBankNotFoundExceptionById;
import com.jsp.bsm.exception.SampleNotFoundException;
import com.jsp.bsm.repository.BloodRepository;
import com.jsp.bsm.repository.SampleRepository;
import com.jsp.bsm.requestdto.SampleRequest;
import com.jsp.bsm.responsedto.SampleResponse;
import com.jsp.bsm.service.SampleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SampleServiceImpl implements SampleService {

    private final SampleRepository sampleRepository;
    private final BloodRepository bloodRepository;

    private SampleResponse mapToSampleResponse(Sample sample) {
        return SampleResponse.builder()
                .sampleId(sample.getSampleId())
                .bloodGroup(sample.getBloodGroup())
                .quantity(sample.getQuantity())
                .availability(sample.isAvailability())
                .emergencyUnits(sample.getEmergencyUnits())
                .availableUnits(sample.getAvailableUnits())
                .build();
    }

    private Sample mapToSample(SampleRequest sampleRequest, Sample sample) {
        sample.setBloodGroup(sampleRequest.getBloodGroup());
        sample.setQuantity(sampleRequest.getQuantity());
        sample.setAvailability(sampleRequest.isAvailability());
        sample.setEmergencyUnits(sampleRequest.getEmergencyUnits());
        sample.setAvailableUnits(sampleRequest.getAvailableUnits());
        return sample;
    }

    @Override
    public SampleResponse addSample(SampleRequest sampleRequest) {
        Sample sample = mapToSample(sampleRequest, new Sample());
        sample = sampleRepository.save(sample);
        return mapToSampleResponse(sample);
    }

    @Override
    public SampleResponse findSampleById(int sampleId) {
        Sample sample = sampleRepository.findById(sampleId)
                .orElseThrow(()-> new SampleNotFoundException("Sample Not Found"));
        return mapToSampleResponse(sample);
    }

    @Override
    public List<SampleResponse> findAllSamples() {
        List<Sample> samples = sampleRepository.findAll();
        if(samples.isEmpty()){
            throw new SampleNotFoundException("Failed to find samples in database");
        } else {
            return samples.stream()
                    .map(this::mapToSampleResponse)
                    .toList();
        }
    }

    @Override
    public SampleResponse updateSampleId(int sampleId, SampleRequest sampleRequest) {
        Sample exSample = sampleRepository.findById(sampleId)
                .orElseThrow(() -> new SampleNotFoundException("Failed to update"));
        Sample sample= this.mapToSample(sampleRequest, exSample);
        Sample updatedSample = sampleRepository.save(sample);
        return  mapToSampleResponse(updatedSample);
    }

    @Override
    public SampleResponse addSampleToBank(SampleRequest sampleRequest, int bankId) {
        BloodBank bloodBank = bloodRepository.findById(bankId)
                .orElseThrow(() -> new BloodBankNotFoundExceptionById("Blood Bank Not Found"));

        Sample sample = Sample.builder()
                .bloodBank(bloodBank)
                .bloodGroup(sampleRequest.getBloodGroup())
                .quantity(sampleRequest.getQuantity())
                .availability(sampleRequest.isAvailability())
                .emergencyUnits(sampleRequest.getEmergencyUnits())
                .availableUnits(sampleRequest.getAvailableUnits())
                .build();

        sampleRepository.save(sample);
        return mapToSampleResponse(sample);
    }

}
