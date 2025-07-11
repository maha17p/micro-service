package com.micro_service.patient.service;

import com.micro_service.patient.dto.PatientResponseDto;
import com.micro_service.patient.mapper.PatientMapper;
import com.micro_service.patient.modal.Patient;
import com.micro_service.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository){
        this.patientRepository  = patientRepository;
    }
    public List<PatientResponseDto> getPatients(){
        List<Patient> patients = patientRepository.findAll();
        return  patients.stream().map(PatientMapper::toDto).toList();
    }
}