package com.jsp.bsm.serviceimpl;

import com.jsp.bsm.entity.Admin;
import com.jsp.bsm.entity.Hospital;
import com.jsp.bsm.enums.Role;
import com.jsp.bsm.exception.HospitalNotFoundException;
import com.jsp.bsm.exception.UserNotFoundExceptionById;
import com.jsp.bsm.repository.AdminRepository;
import com.jsp.bsm.repository.HospitalRepository;
import com.jsp.bsm.repository.UserRepository;
import com.jsp.bsm.requestdto.HospitalRequest;
import com.jsp.bsm.responsedto.HospitalResponse;
import com.jsp.bsm.service.HospitalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    private HospitalResponse mapToHospitalResponse(Hospital hospital) {
        return HospitalResponse.builder()
                .hospitalId(hospital.getHospitalId())
                .name(hospital.getName())
                .build();
    }

    private Hospital mapToHospital(HospitalRequest hospitalRequest, Hospital hospital) {
        hospital.setName(hospitalRequest.getName());
        return hospital;
    }

    @Override
    public HospitalResponse addHospital(HospitalRequest hospitalRequest) {
        Hospital hospital = this.mapToHospital(hospitalRequest, new Hospital());
        hospital = hospitalRepository.save(hospital);
        return this.mapToHospitalResponse(hospital);
    }


    @Override
    public HospitalResponse findHospitalById(int hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalNotFoundException("Hospital Not Found"));
        return this.mapToHospitalResponse(hospital);
    }

    @Override
    public HospitalResponse updateHospitalById(int hospitalId, HospitalRequest hospitalRequest) {
        Hospital exHospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalNotFoundException("Failed to update"));
        Hospital hospital = this.mapToHospital(hospitalRequest, exHospital);

        Hospital updatedHospital = hospitalRepository.save(hospital);
        return  mapToHospitalResponse(updatedHospital);
    }


    @Override
    public HospitalResponse addAdminHospital(HospitalRequest hospitalRequest, int adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(()-> new UserNotFoundExceptionById("Admin Not Found"));
        List<Admin> admins = new ArrayList<>();
        admins.add(admin);
        Hospital hospital = Hospital.builder()
                .admin(admins)
                .name(hospitalRequest.getName())
                .build();
        hospital=hospitalRepository.save(hospital);
        return this.mapToHospitalResponse(hospital);
    }
}
