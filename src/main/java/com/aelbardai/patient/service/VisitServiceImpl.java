package com.aelbardai.patient.service;

import com.aelbardai.patient.domain.Patient;
import com.aelbardai.patient.domain.Visit;
import com.aelbardai.patient.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Implementation of VisitService.class
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VisitServiceImpl implements VisitService{

    private final VisitRepository visitRepository;
    private final PatientService patientService;

    @Override
    public <T extends Visit> T addVisit(T visit) {
        return (T) visitRepository.save(visit);
    }

    @Override
    public <T extends Visit> T getVisitById(Long id) {
        return (T)visitRepository.findOne(id);
    }

    @Override
    public <T extends Visit> List<T> getAllVisits() {
        return (List<T>)visitRepository.findAll();
    }

    @Override
    public <T extends Visit> List<T> getVisitsByPatient(Long patientId) {
        Patient patient = patientService.getPatientById(patientId);
        if(patient ==null){
            log.info("No patient registred with id '{}'" , patientId);
            return Collections.emptyList();
        }
        else{
            return (List<T>)visitRepository.findByPatient(patient);
        }
    }

    @Override
    public void deleteVisit(Long id) {
        visitRepository.delete(id);
    }
}
