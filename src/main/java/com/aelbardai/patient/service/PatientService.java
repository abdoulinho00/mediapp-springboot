package com.aelbardai.patient.service;

import com.aelbardai.patient.domain.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Service interface for Patient.class entity
 */
public interface PatientService{

    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    Patient savePatient(Patient patient);
    void deletePatient(Long id);
}
