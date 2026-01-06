package com.eventmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.eventmanagement.service.RegistrationService;

@Controller
@RequestMapping("/student")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/register/{eventId}")
    public String registerEvent(@PathVariable Long eventId,
                                Authentication authentication,
                                Model model) {

        String message = registrationService.registerForEvent(
                eventId, authentication.getName());

        model.addAttribute("message", message);
        return "student/registration-result";
    }

    @GetMapping("/my-registrations")
    public String myRegistrations(Authentication authentication, Model model) {

        model.addAttribute("registrations",
                registrationService.getRegistrationsByUser(authentication.getName()));

        return "student/my-registrations";
    }
   
}
