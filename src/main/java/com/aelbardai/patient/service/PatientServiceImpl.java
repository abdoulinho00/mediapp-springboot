package com.aelbardai.patient.service;

import com.aelbardai.patient.domain.Patient;
import com.aelbardai.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation for PatientService interface
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientServiceImpl implements PatientService{

    private final PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {
        return (List<Patient>)patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findOne(id);
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patient==null?patient:patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.delete(id);
    }
}
