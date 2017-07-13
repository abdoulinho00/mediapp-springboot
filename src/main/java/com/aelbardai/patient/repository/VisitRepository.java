package com.aelbardai.patient.repository;

import com.aelbardai.patient.domain.Patient;
import com.aelbardai.patient.domain.Visit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Visit.class entity
 */
@Repository
public interface VisitRepository extends CrudRepository<Visit, Long >{
    List<Visit> findByPatient(Patient patient);
}
