package com.eventmanagement.controller;

import com.eventmanagement.model.Role;
import com.eventmanagement.model.User;
import com.eventmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // ================= LOGIN =================
    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    // ================= REGISTER PAGE =================
    @GetMapping("/register")
    public String registerPage(Model model) {

        model.addAttribute("user", new User());

        long adminCount = userService.countAdmins();

        model.addAttribute("adminLimitReached", adminCount >= 4);
        model.addAttribute("roles", Role.values());

        return "auth/register";
    }

    // ================= REGISTER SUBMIT =================
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {

        if (user.getRole() == Role.ADMIN && userService.countAdmins() >= 4) {
            model.addAttribute("error", "Admin limit reached (Max 4 admins allowed)");
            return "auth/register";
        }

        if (user.getRole() == Role.ADMIN) {
            user.setDepartment(null);
        }

        userService.registerUser(user);

        model.addAttribute("success", "Registration successful! Please login.");
        return "auth/login";
    }
}
