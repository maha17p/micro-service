package com.micro_service.patient.service;

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
    public List<Patient> getPatients(){
        return patientRepository.findAll();
    }
}