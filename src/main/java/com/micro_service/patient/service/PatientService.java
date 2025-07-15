package com.micro_service.patient.service;

import com.micro_service.patient.dto.PatientRequestDto;
import com.micro_service.patient.dto.PatientResponseDto;
import com.micro_service.patient.exception.ResourceAlreadyExistsException;
import com.micro_service.patient.exception.ResourceNotFoundException;
import com.micro_service.patient.mapper.PatientMapper;
import com.micro_service.patient.modal.Patient;
import com.micro_service.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto){
        if(patientRepository.existsByEmail(patientRequestDto.getEmail())){
             throw  new ResourceAlreadyExistsException(
                     "A patient with this email " + "already exists "
                             + patientRequestDto.getEmail());
        }
        Patient patient = PatientMapper.toModal(patientRequestDto);
        patientRepository.save(patient);
        return  PatientMapper.toDto(patient);
    }

    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }

    public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto) {
        Patient patient = patientRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Patient is not found with ID:" + id)
                );
        if(patientRepository.existsByEmailAndIdNot(patientRequestDto.getEmail(),id)){
            throw  new ResourceAlreadyExistsException(
                    "A patient with this email " + "already exists "
                            + patientRequestDto.getEmail());
        }

        patient.setName(patientRequestDto.getName());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setAddress(patientRequestDto.getAddress());
        patient.setDateOfBirth(patientRequestDto.getDateOfBirth());
        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDto(updatedPatient);
    }
}