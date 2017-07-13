package com.aelbardai.web;

import com.aelbardai.patient.domain.NutritionVisit;
import com.aelbardai.patient.domain.Patient;
import com.aelbardai.patient.domain.Visit;
import com.aelbardai.patient.service.PatientService;
import com.aelbardai.patient.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Controller for medical visits : {General , nutrition , Esthetic} : add/ edit list / delete
 */
@Controller
@RequestMapping("/visits")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VisitController {

    private final VisitService visitService;
    private final PatientService patientService;

    @GetMapping("/add/{patientId}")
    public ModelAndView addVisitForm(@PathVariable("patientId") Long patientId , @RequestParam(value= "id" ,required= false) Long id){
        log.info("patient id  : {} , visit id : {}" , patientId , id);
        Visit visit= null;
        if(id != null ){
            visit = visitService.getVisitById(id);
            if(visit ==null){
                log.info("No visit found with id : '{}'" , id);
                visit = new NutritionVisit();
            }
        }
        else{
            Patient patient = patientService.getPatientById(patientId);
            if(patient ==null){
                return new ModelAndView("redirect:/patients");
            }
            else{
                visit = new NutritionVisit();
                visit.setPatient(patient);
            }
        }
        return new ModelAndView("visits/add" , "visit" , visit);
    }

    @PostMapping("/add")
    public ModelAndView addVisist(Visit visit,BindingResult bindingResult, Model model){
        log.info("trying to save object : {}", visit);
        visitService.addVisit(visit);
        return new ModelAndView("redirect:/patients");
    }
}
