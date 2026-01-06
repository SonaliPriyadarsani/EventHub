package com.eventmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.eventmanagement.service.EventService;
import com.eventmanagement.service.RegistrationService;
import com.eventmanagement.util.SecurityUtil;

@Controller
public class StudentController {

    @Autowired
    private EventService eventService;

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/student/dashboard")
    public String studentDashboard(Model model, Authentication authentication) {

        model.addAttribute("events", eventService.getApprovedEvents());
        model.addAttribute("registrations",
                registrationService.getRegistrationsByUser(authentication.getName()));
        model.addAttribute("username", authentication.getName());

        return "student/student-dashboard";
    }
    
    @GetMapping("/student/events")
    public String viewApprovedEvents(Model model) {
        model.addAttribute("events", eventService.getApprovedEvents());
        return "student/event-list";
    }

    
    @PostMapping("/student/register/{id}")
    public String registerEvent(@PathVariable Long id) {

        String studentEmail = SecurityUtil.getLoggedInUserEmail();
        registrationService.register(id, studentEmail);

        return "redirect:/student/my-registrations";
    }


    @GetMapping("/student/feedback")
    public String feedbackForm() {
        return "student/feedback-form";
    }


}
