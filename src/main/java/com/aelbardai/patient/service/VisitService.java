package com.aelbardai.patient.service;

import com.aelbardai.patient.domain.Visit;

import java.util.List;

/**
 * Interface for visit service
 */
public interface VisitService {

    <T extends Visit> T addVisit(T visit);
    <T extends Visit> T getVisitById(Long id);
    <T extends Visit> List<T> getAllVisits();
    <T extends Visit> List<T> getVisitsByPatient(Long patientId);
    void deleteVisit(Long id);
}
