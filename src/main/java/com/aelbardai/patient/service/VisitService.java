package com.aelbardai.patient.service;

import com.aelbardai.patient.domain.Visit;

import java.util.List;

/**
 * Interface for visit service
 */
public interface VisitService {

    Visit addVisit(Visit visit);
    Visit getVisitById(Long id);
    List<Visit> getAllVisits();
    List<Visit> getVisitsByPatient(Long patientId);
    void deleteVisit(Long id);
}
