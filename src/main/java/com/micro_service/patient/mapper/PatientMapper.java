package com.micro_service.patient.mapper;

import com.micro_service.patient.dto.PatientResponseDto;
import com.micro_service.patient.modal.Patient;

import java.util.List;

public class PatientMapper {

    public static PatientResponseDto toDto(Patient patient){
        PatientResponseDto patientResponseDto = new PatientResponseDto();
        patientResponseDto.setId(patient.getId());
        patientResponseDto.setName(patient.getName());
        patientResponseDto.setEmail(patient.getEmail());
        patientResponseDto.setAddress(patient.getAddress());
        patientResponseDto.setDateOfBirth(patient.getDateOfBirth());
        return patientResponseDto;

    };
}
