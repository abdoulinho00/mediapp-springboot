package com.aelbardai.web;

import com.aelbardai.patient.domain.*;
import com.aelbardai.patient.service.PatientService;
import com.aelbardai.patient.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
    public  ModelAndView addGeneralVisit(@ModelAttribute("generalVisit")GeneralVisit generalVisit, BindingResult bindingResult, Model model){
        log.info("trying to save object : {}", generalVisit);
        visitService.addVisit(generalVisit);
        return new ModelAndView("redirect:/patients");
    }

    @PostMapping("/add/nutrition")
    public  ModelAndView addNutritionVisit(
            @RequestParam("beforeImage") MultipartFile beforeImage,
            @RequestParam("afterImage") MultipartFile afterImage,
            @ModelAttribute("nutritionVisit") NutritionVisit nutritionVisit,
            BindingResult bindingResult, Model model){
        log.info("trying to save object : {}", nutritionVisit);
        visitService.addNutritionVisit(nutritionVisit , beforeImage , afterImage);
        return new ModelAndView("redirect:/patients");
    }

    @PostMapping("/add/esthetic")
    public  ModelAndView addEstheticVisit(@ModelAttribute("estheticVisit") EstheticVisit estheticVisit, BindingResult bindingResult, Model model){
        log.info("trying to save object : {}");
        visitService.addVisit(estheticVisit);
        return new ModelAndView("redirect:/patients");
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewVisitDetails(@PathVariable("id") Long id){
        Visit visit = visitService.getVisitById(id);
        if(visit == null){
            return new ModelAndView("error/404" , "message" , "No visit founf for your request");
        }
        else{
            return new ModelAndView("visits/details" , "visit" , visit);
        }
    }

    @GetMapping("/image/{id}")
    @ResponseBody
    public FileSystemResource serveFile(HttpServletRequest request, HttpServletResponse response , @PathVariable("id") Long visitId, @RequestParam("image") String type) {

        Visit visit = visitService.getVisitById(visitId);
        String path = null;
        switch (type){
            case "before" : if(visit instanceof NutritionVisit){path= ((NutritionVisit) visit).getBeforePath();}
                            break;
            case "after" : if(visit instanceof NutritionVisit){path= ((NutritionVisit) visit).getAfterPath();}
                            break;
            case "left" : if(visit instanceof EstheticVisit){path= ((EstheticVisit) visit).getLeftProfilePath();}
                break;
            case "right" : if(visit instanceof EstheticVisit){path= ((EstheticVisit) visit).getRightProfilePath();}
                break;
            case "face" : if(visit instanceof EstheticVisit){path= ((EstheticVisit) visit).getFacePath();}
                break;
            default: break;

        }

            if(path != null) {
                try {


                    //response.setContentType("im");
                    //response.setHeader("Content-Disposition", String.format("inline; filename=%s.%s",fileElement.getName(),fileElement.getExtension()));
                    //return new FileSystemResource(file);
                    response.setContentType("image/jpg");
                    response.getOutputStream().write(Files.readAllBytes(Paths.get(path)));
                    //return new FileSystemResource(new File(path));
                }catch(IOException ex){
                    log.error("couldn't write in output stream");
                    return null;
                }
                catch(Exception ex){
                    log.error("something wrong with my file");
                }
            }
            else{
                return null;
            }


        return null;
    }
}
