package com.healconnect.controller;

import com.healconnect.model.Credentials;
import com.healconnect.model.Role;
import com.healconnect.model.User;
import com.healconnect.service.CredentialsService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private CredentialsService credentialsService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());
        model.addAttribute("roles", Role.values());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult userBindingResult,
                           @Valid @ModelAttribute("credentials") Credentials credentials,
                           BindingResult credentialsBindingResult,
                           Model model) {

        if (userBindingResult.hasErrors() || credentialsBindingResult.hasErrors()) {
            return "auth/register";
        }

        credentials.setUser(user);
        credentialsService.save(credentials);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/logout-success")
    public String logoutPage() {
        return "auth/logout";
    }
}
