package com.aelbardai.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dev on 7/11/17.
 */
@Controller
@RequestMapping("/")
public class HomeController {

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
        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    public ModelAndView signup(){
        return new ModelAndView("redirect:/");
    }
}
