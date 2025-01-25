package com.jsp.bsm.service;

import com.jsp.bsm.requestdto.HospitalRequest;
import com.jsp.bsm.responsedto.HospitalResponse;
import jakarta.validation.Valid;

public interface HospitalService {
    HospitalResponse addHospital(HospitalRequest hospitalRequest);

    HospitalResponse findHospitalById(int hospitalId);

    HospitalResponse updateHospitalById(int hospitalId, HospitalRequest hospitalRequest);

    HospitalResponse addAdminHospital(HospitalRequest hospitalRequest, int adminId);
}
