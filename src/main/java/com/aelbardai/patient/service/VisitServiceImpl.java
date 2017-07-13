package com.aelbardai.patient.service;

import com.aelbardai.patient.domain.*;
import com.aelbardai.patient.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    @Value("${file-manager.base-folder}")
    private String baseFolder;

    @Override
    public GeneralVisit addGeneralVisit(GeneralVisit generalVisit) {
        return addVisit(generalVisit);
    }

    @Override
    public NutritionVisit addNutritionVisit(NutritionVisit nutritionVisit, MultipartFile beforeImage, MultipartFile afterImage) {
        if(beforeImage != null && beforeImage.getContentType().startsWith("image")){
            File beforeSaved = saveFile(beforeImage,new File(String.format("%s/%d/%s" , baseFolder , nutritionVisit.getPatient().getId() , beforeImage.getOriginalFilename())));
            nutritionVisit.setBeforePath(beforeSaved.getAbsolutePath());
        }
        if(afterImage != null && afterImage.getContentType().startsWith("image")){
            File afterSaved = saveFile(afterImage,new File(String.format("%s/%d/%s" , baseFolder , nutritionVisit.getPatient().getId() , afterImage.getOriginalFilename())));
            nutritionVisit.setAfterPath(afterSaved.getAbsolutePath());
        }
        return addVisit(nutritionVisit);
    }

    @Override
    public EstheticVisit addEstheticVisit(EstheticVisit estheticVisit, MultipartFile leftProfile, MultipartFile rightProfile, MultipartFile faceProfile) {
        if(leftProfile != null && leftProfile.getContentType().startsWith("image")){
            File leftProfileSaved = saveFile(leftProfile,new File(String.format("%s/%d/%s" , baseFolder , estheticVisit.getPatient().getId() , leftProfile.getOriginalFilename())));
            estheticVisit.setLeftProfilePath(leftProfileSaved.getAbsolutePath());
        }
        if(rightProfile != null && rightProfile.getContentType().startsWith("image")){
            File rightProfileSaved = saveFile(rightProfile,new File(String.format("%s/%d/%s" , baseFolder , estheticVisit.getPatient().getId() , rightProfile.getOriginalFilename())));
            estheticVisit.setRightProfilePath(rightProfileSaved.getAbsolutePath());
        }
        if(faceProfile != null && faceProfile.getContentType().startsWith("image")){
            File faceProfileSaved = saveFile(faceProfile,new File(String.format("%s/%d/%s" , baseFolder , estheticVisit.getPatient().getId() , faceProfile.getOriginalFilename())));
            estheticVisit.setFacePath(faceProfileSaved.getAbsolutePath());
        }
        return addVisit(estheticVisit);
    }

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

    private File findFileName(File file) {
        if (!file.exists()) {
            return file;
        } else {
            String absoluthPath = file.getAbsolutePath();
            String filename = String.format("%s%s", FilenameUtils.getFullPath(absoluthPath), FilenameUtils.getBaseName(absoluthPath));
            String extension = FilenameUtils.getExtension(absoluthPath);
            log.info("filename : {} , extension : {}", filename, extension);
            int i = 1;
            File fileTosave = new File(String.format("%s(%d).%s", filename, i, extension));
            while (fileTosave.exists()) {
                i++;
                fileTosave = new File(String.format("%s(%d).%s", filename, i, extension));
            }
            return fileTosave;
        }
    }

    private File saveFile(MultipartFile file ,File savedfile){
        try {
            savedfile = findFileName(savedfile);
            log.info("savedfile : {}", savedfile.getAbsolutePath());
            File parent = savedfile.getParentFile();
            if (!parent.exists() && !parent.mkdirs()) {
                throw new IllegalStateException("Couldn't create dir: " + parent);
            }
            file.transferTo(savedfile);
            return savedfile;
        } catch (IOException e) {
            log.error("Couldn't write uploaded file ", e);
            return null;
        }
    }
}
