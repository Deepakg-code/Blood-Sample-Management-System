package com.jsp.bsm.controller;

import com.jsp.bsm.requestdto.BloodBankRequest;
import com.jsp.bsm.requestdto.SampleRequest;
import com.jsp.bsm.responsedto.BloodBankResponse;
import com.jsp.bsm.responsedto.SampleResponse;
import com.jsp.bsm.service.SampleService;
import com.jsp.bsm.utility.ResponseStructure;
import com.jsp.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SampleController {

    private SampleService sampleService;
    private RestResponseBuilder responseBuilder;

    @PostMapping("/samples")
    public ResponseEntity<ResponseStructure<SampleResponse>> addBloodBank(@RequestBody SampleRequest sampleRequest){
         SampleResponse sampleResponse = sampleService.addSample(sampleRequest);
        return responseBuilder.success(HttpStatus.CREATED, "Sample Created", sampleResponse);
    }

    @GetMapping("/samples/{sampleId}")
    public ResponseEntity<ResponseStructure<SampleResponse>> findSampleById(@PathVariable int sampleId){
        SampleResponse sampleResponse = sampleService.findSampleById(sampleId);
        return responseBuilder.success(HttpStatus.FOUND, "BloodBank Found", sampleResponse);
    }

    @GetMapping("/samples")
    public ResponseEntity<ResponseStructure<List<SampleResponse>>> findAllSamples(){
        List<SampleResponse> sampleResponses = sampleService.findAllSamples();
        return responseBuilder.success(HttpStatus.FOUND, "Samples Found", sampleResponses);
    }

    @PutMapping("/samples/{sampleId}")
    public ResponseEntity<ResponseStructure<SampleResponse>> updateBloodBankById(@PathVariable int sampleId, @RequestBody SampleRequest sampleRequest){
        SampleResponse sampleResponse = sampleService.updateSampleId(sampleId, sampleRequest);
        return responseBuilder.success(HttpStatus.FOUND, "Sample Updated", sampleResponse);
    }

    @PostMapping("/samples-bank/{bankId}")
    public ResponseEntity<ResponseStructure<SampleResponse>> addSampleToBank(@RequestBody SampleRequest sampleRequest, @PathVariable int bankId){
        SampleResponse sampleResponse = sampleService.addSampleToBank(sampleRequest, bankId);
        return responseBuilder.success(HttpStatus.CREATED, "Samples to blood bank added", sampleResponse);
    }


}
