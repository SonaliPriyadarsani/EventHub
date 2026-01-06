package com.eventmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.eventmanagement.model.Feedback;
import com.eventmanagement.service.FeedbackService;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // Student: feedback form
    @GetMapping("/student/feedback/{eventId}")
    public String feedbackForm(@PathVariable Long eventId, Model model) {
        model.addAttribute("eventId", eventId);
        return "student/feedback-form";
    }

    // Student: submit feedback
    @PostMapping("/student/submit-feedback")
    public String submitFeedback(@RequestParam Long eventId,
                                 @RequestParam int rating,
                                 @RequestParam String comments,
                                 Authentication authentication) {

        feedbackService.submitFeedback(
                eventId, authentication.getName(), rating, comments);

        return "redirect:/student/dashboard";
    }

    // Admin: view feedback
    @GetMapping("/admin/feedback/{eventId}")
    public String viewFeedback(@PathVariable Long eventId, Model model) {

        model.addAttribute("feedbackList",
                feedbackService.getFeedbackByEvent(eventId));

        return "admin/view-feedback";
    }
    @PostMapping("/student/feedback")
    public String submitFeedback(Feedback feedback) {
        feedbackService.saveFeedback(feedback);
        return "redirect:/student/dashboard";
    }

}
