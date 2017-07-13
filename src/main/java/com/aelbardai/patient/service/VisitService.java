package com.aelbardai.patient.service;

import com.aelbardai.patient.domain.EstheticVisit;
import com.aelbardai.patient.domain.GeneralVisit;
import com.aelbardai.patient.domain.NutritionVisit;
import com.aelbardai.patient.domain.Visit;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Interface for visit service
 */
public interface VisitService {

    GeneralVisit addGeneralVisit(GeneralVisit generalVisit);
    NutritionVisit addNutritionVisit(NutritionVisit nutritionVisit , MultipartFile beforeImage , MultipartFile afterImage);
    EstheticVisit addEstheticVisit(EstheticVisit estheticVisit , MultipartFile leftProfile , MultipartFile rightProfile , MultipartFile faceProfile);
    <T extends Visit> T addVisit(T visit);
    <T extends Visit> T getVisitById(Long id);
    <T extends Visit> List<T> getAllVisits();
    <T extends Visit> List<T> getVisitsByPatient(Long patientId);
    void deleteVisit(Long id);
}
