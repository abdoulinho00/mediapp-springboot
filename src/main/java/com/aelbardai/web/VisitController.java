package com.aelbardai.web;

import com.aelbardai.patient.domain.*;
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
    public <T extends Visit> ModelAndView addVisitForm(@PathVariable("patientId") Long patientId , @RequestParam(value= "id" ,required= false) Long id){
        log.info("patient id  : {} , visit id : {}" , patientId , id);
        GeneralVisit generalVisit= null;
        NutritionVisit nutritionVisit =null;
        EstheticVisit estheticVisit = null;
        if(id != null ){
            log.info("Edit an existing visit entry");
            Visit visit = visitService.getVisitById(id);
            if(visit ==null){
                log.info("No visit found with id : '{}'" , id);
                return new ModelAndView("error/404" , "message" , "No visit found");
            }
            else{
                if(visit instanceof GeneralVisit){
                    generalVisit = (GeneralVisit) visit;
                }
                if(visit instanceof NutritionVisit){
                    nutritionVisit =(NutritionVisit) visit;
                }
                if(visit instanceof EstheticVisit){
                    estheticVisit = (EstheticVisit) visit;
                }
                ModelAndView modelAndView = new ModelAndView("visits/add" , "generalVisit" , generalVisit);
                modelAndView.addObject("nutritionVisit" ,nutritionVisit);
                modelAndView.addObject("estheticVisit" ,estheticVisit);
                return modelAndView;
            }

        }
        else{
            log.info("Adding new Visit entry");
            Patient patient = patientService.getPatientById(patientId);
            if(patient ==null){
                log.info("No patient entry found with id '{}'" , patientId);
                return new ModelAndView("error/404" , "message" , "No patient found");
            }
            else{
                log.info("Constructing new visit objects");
                generalVisit = GeneralVisit.builder().build();
                generalVisit.setPatient(patient);
                nutritionVisit = NutritionVisit.builder().build();
                nutritionVisit.setPatient(patient);
                estheticVisit = EstheticVisit.builder().build();
                estheticVisit.setPatient(patient);
                ModelAndView modelAndView = new ModelAndView("visits/add" , "generalVisit" , generalVisit);
                modelAndView.addObject("nutritionVisit" , nutritionVisit);
                modelAndView.addObject("estheticVisit" , estheticVisit);
                return modelAndView;
            }

        }

    }

    @PostMapping("/add/general")
    public  ModelAndView addGeneralVisist(@ModelAttribute("generalVisit")GeneralVisit generalVisit, BindingResult bindingResult, Model model){
        log.info("trying to save object : {}", generalVisit);
        visitService.addVisit(generalVisit);
        return new ModelAndView("redirect:/patients");
    }

    @PostMapping("/add/nutrition")
    public  ModelAndView addNutritionVisist(@ModelAttribute("nutritionVisit") NutritionVisit nutritionVisit, BindingResult bindingResult, Model model){
        log.info("trying to save object : {}", nutritionVisit);
        visitService.addVisit(nutritionVisit);
        return new ModelAndView("redirect:/patients");
    }

    @PostMapping("/add/esthetic")
    public  ModelAndView addEstheticVisist(@ModelAttribute("estheticVisit") EstheticVisit estheticVisit, BindingResult bindingResult, Model model){
        log.info("trying to save object : {}");
        visitService.addVisit(estheticVisit);
        return new ModelAndView("redirect:/patients");
    }
}
