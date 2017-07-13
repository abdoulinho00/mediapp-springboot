package com.aelbardai.web;

import com.aelbardai.patient.domain.Patient;
import com.aelbardai.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for patient controller : add , edit , list , remove
 */
@Controller
@RequestMapping("/patients")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientController {

    private final PatientService patientService;

    @GetMapping({"/list",""})
    public ModelAndView listAllPatient(){
        return new ModelAndView("patients/list" , "patients" , patientService.getAllPatients());
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewPatientDetails(@PathVariable("id") Long id){
        Patient patient  = patientService.getPatientById(id);
        if(patient != null) {
            return new ModelAndView("patients/details", "patient", patient);
        }
        else{
            return new ModelAndView("error/404");
        }
    }

    @GetMapping("/add")
    public ModelAndView addPatientForm(@RequestParam(value="id" , required = false) Long id){
        Patient patient =null;
        if(id !=null){
            patient = patientService.getPatientById(id);
        }
        return new ModelAndView("patients/add" , "patient" , patient==null?new Patient():patient) ;
    }

    @PostMapping("/add")
    public ModelAndView addPatient(Patient patient){
        log.info("patient : {]", patient);
        patientService.savePatient(patient);
        return new ModelAndView("redirect:/patients");
    }

    @PostMapping("/delete")
    public ModelAndView removePatient(@RequestParam("id") Long id){
        patientService.deletePatient(id);
        return new ModelAndView("redirect:/patients");
    }

}
