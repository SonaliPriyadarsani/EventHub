package com.eventmanagement.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.eventmanagement.model.User;
import com.eventmanagement.service.EventService;
import com.eventmanagement.service.ReportService;
import com.eventmanagement.service.UserService;

@Controller
public class AdminController {

    @Autowired
    private ReportService reportService;
    
    @Autowired
    private EventService eventService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin/admin-dashboard";
    }
    
    @PostMapping("/admin/approve/{id}")
    public String approveEvent(@PathVariable Long id) {
        eventService.approveEvent(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/approve-events")
    public String showPendingEvents(Model model) {
        model.addAttribute("events", eventService.getCoordinatorPendingEvents());
        return "admin/approve-events";
    }

    @GetMapping("/admin/reports")
    public String viewReports(Model model) {

        model.addAttribute("totalUsers", reportService.getTotalUsers());
        model.addAttribute("totalEvents", reportService.getTotalEvents());
        model.addAttribute("totalRegistrations",
                reportService.getTotalRegistrations());
        model.addAttribute("eventReport",
                reportService.getRegistrationsPerEvent());

        return "admin/reports";
    }

    @PostMapping("/admin/deactivate-user/{id}")
    public String deactivateUser(@PathVariable Long id) {
        userService.deactivateUser(id);
        return "redirect:/admin/manage-users";
    }

    @PostMapping("/admin/activate-user/{id}")
    public String activateUser(@PathVariable Long id) {
        userService.activateUser(id);
        return "redirect:/admin/manage-users";
    }
    
    @GetMapping("/admin/manage-users")
    public String manageUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/manage-users";
    }

    @PostMapping("/admin/update-role")
    public String updateUserRole(
            @RequestParam Long userId,
            @RequestParam String role) {

        userService.updateUserRole(userId, role);
        return "redirect:/admin/manage-users";
    }

    @PostMapping("/admin/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/manage-users";
    }
    
   
}
