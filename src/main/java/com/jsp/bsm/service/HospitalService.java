package com.jsp.bsm.service;

import com.jsp.bsm.requestdto.HospitalRequest;
import com.jsp.bsm.responsedto.HospitalResponse;

public interface HospitalService {
    HospitalResponse addHospital(HospitalRequest hospitalRequest);

    HospitalResponse findHospitalById(int hospitalId);

    HospitalResponse updateHospitalById(int hospitalId, HospitalRequest hospitalRequest);
}
