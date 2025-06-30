package com.healconnect.controller;

import com.healconnect.model.Credentials;
import com.healconnect.model.Role;
import com.healconnect.model.User;
import com.healconnect.service.CredentialsService;
import com.healconnect.service.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final AppointmentController appointmentController;

	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private UserService userService;

    AuthController(AppointmentController appointmentController) {
        this.appointmentController = appointmentController;
    }

	@GetMapping({"/register", "/register/{role}"})
	public String showRegisterForm(@PathVariable(required = false) Role role, Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());

		if (role != null) 
			model.addAttribute("roles", new Role[]{role});
		else 
			model.addAttribute("roles", Role.values());

		return "auth/register";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") User user,
			BindingResult userBindingResult,
			@Valid @ModelAttribute("credentials") Credentials credentials,
			BindingResult credentialsBindingResult,
			Model model) {

		if (userBindingResult.hasErrors() || credentialsBindingResult.hasErrors()) 
			return "auth/register";

		credentials.setUser(user);
		user.setCredentials(credentials);
		userService.save(user);
		credentialsService.save(credentials);

		if (credentials.getRole() == Role.ADMIN)
			return "redirect:/login";

		return "redirect:/" + credentials.getRole().name().toLowerCase() + "/complete-registration/" + user.getId() ;
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
