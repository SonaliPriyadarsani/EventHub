package com.eventmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.eventmanagement.model.Event;
import com.eventmanagement.model.EventStatus;
import com.eventmanagement.service.EventService;

@Controller
@RequestMapping("/coordinator")
public class CoordinatorController {

    @Autowired
    private EventService eventService;

    @GetMapping("/dashboard")
    public String coordinatorDashboard(Model model, Authentication auth) {
        model.addAttribute("events", eventService.getEventsByCoordinator(auth.getName()));
        model.addAttribute("username", auth.getName());
        return "coordinator/coordinator_dashboard";
    }

    @GetMapping("/create-event")
    public String createEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "coordinator/create-event";
    }
    
    @PostMapping("/save-event")
    public String saveEvent(
            @ModelAttribute Event event,
            Authentication auth) {

        eventService.createEvent(event, auth.getName());
        return "redirect:/coordinator/manage-events";
    }
    
    @GetMapping("/manage-events")
    public String manageEvents(Model model, Authentication auth) {
        model.addAttribute("events",
                eventService.getEventsByCoordinator(auth.getName()));
        return "coordinator/manage-events";
    }
    @GetMapping("/delete-event/{id}")
    public String deleteEvent(@PathVariable Long id, Authentication auth) {

        Event event = eventService.getEventById(id);

        // Safety check: only owner can delete
        if (event != null &&
            event.getCreatedBy().getEmail().equals(auth.getName()) &&
            event.getStatus() == EventStatus.PENDING) {

            eventService.deleteEvent(id);
        }

        return "redirect:/coordinator/manage-events";
    }
    
    
    @GetMapping("/edit-event/{id}")
    public String editEventForm(@PathVariable Long id,
                                Model model,
                                Authentication auth) {

        Event event = eventService.getEventById(id);

        // safety: only creator can edit
        if (event == null ||
            !event.getCreatedBy().getEmail().equals(auth.getName())) {
            return "redirect:/coordinator/dashboard";
        }

        model.addAttribute("event", event);
        return "coordinator/edit-event";
    }
    @PostMapping("/update-event")
    public String updateEvent(@ModelAttribute Event event,
                              Authentication auth) {

        Event existing = eventService.getEventById(event.getId());

        if (existing != null &&
            existing.getCreatedBy().getEmail().equals(auth.getName())) {

            eventService.updateEvent(event);
        }

        return "redirect:/coordinator/manage-events";
    }

    @GetMapping("/view-registrations/{id}")
    public String viewRegistrations(@PathVariable Long id,
                                    Model model,
                                    Authentication auth) {

        Event event = eventService.getEventById(id);

        if (event == null ||
            !event.getCreatedBy().getEmail().equals(auth.getName())) {
            return "redirect:/coordinator/dashboard";
        }

        model.addAttribute("event", event);
        model.addAttribute("registrations",
                eventService.getRegistrationsByEvent(id));

        return "coordinator/event-registrations";
    }


}
