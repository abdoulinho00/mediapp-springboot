package com.aelbardai.web;

import com.aelbardai.user.domain.Role;
import com.aelbardai.user.domain.UserCreateForm;
import com.aelbardai.user.service.UserCreateFormValidator;
import com.aelbardai.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Home controller : home page , login and signup new user
 */
@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HomeController {

    private final UserService userService;
    private final UserCreateFormValidator userCreateFormValidator;

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }
    @GetMapping("")
    public ModelAndView home(){
        return new ModelAndView("index");
    }

    @GetMapping("/login")
    public ModelAndView loginForm(){
        return new ModelAndView("login");
    }

    @GetMapping("/signup")
    public ModelAndView signupForm(){
        return new ModelAndView("signup","form", new UserCreateForm());
    }

    @PostMapping("/signup")
    public ModelAndView signup(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult){
        log.info("trying to signup new user : {}" , form.getEmail());
        if (bindingResult.hasErrors()) {
            return new ModelAndView("signup");
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            log.debug("email {} already exist" , form.getEmail(), e);
            bindingResult.reject("email.exists", "Email already exists");
            return new ModelAndView("signup");
        }
        return new ModelAndView("redirect:/");
    }
}
