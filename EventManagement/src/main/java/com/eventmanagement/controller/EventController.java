package com.eventmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.eventmanagement.model.Event;
import com.eventmanagement.service.EventService;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    // Show create event form
    @GetMapping("/create-event")
    public String createEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "coordinator/create-event";
    }

    // Save event
    @PostMapping("/coordinator/save-event")
    public String saveEvent(Event event, Authentication auth) {
        event.setCoordinator(auth.getName());   // 🔴 VERY IMPORTANT
        eventService.createEvent(event, auth.getName());
        return "redirect:/coordinator/manage-events";
    }

    // Delete event
    @GetMapping("/coordinator/delete-event/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/coordinator/manage-events";
    }

    // Student: view approved events
    @GetMapping("/student/events")
    public String viewEvents(Model model) {
        model.addAttribute("events", eventService.getApprovedEvents());
        return "student/event-list";
    }
    
    @GetMapping("/coordinator/approve-events")
    public String approveEvents(Model model) {
        model.addAttribute("events", eventService.getCoordinatorPendingEvents());
        return "coordinator/approve-events";
    }
    
    @GetMapping("/coordinator/manage-events")
    public String manageEvents(Model model, Authentication authentication) {

        String coordinatorName = authentication.getName();

        model.addAttribute(
            "events",
            eventService.getEventsByCoordinator(coordinatorName)
        );

        return "coordinator/manage-events";
    }

}
