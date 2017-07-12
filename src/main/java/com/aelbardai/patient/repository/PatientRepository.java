package com.aelbardai.patient.repository;

import com.aelbardai.patient.domain.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Patient.class
 */
@Repository
public interface PatientRepository extends CrudRepository<Patient, Long>{
}
