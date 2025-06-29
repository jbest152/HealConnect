package com.healconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.healconnect.model.Credentials;
import com.healconnect.model.User;
import com.healconnect.service.CredentialsService;
import com.healconnect.service.DoctorService;
import com.healconnect.service.PatientService;

@Controller
public class HomeController {
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private PatientService patientService;
	
	@GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails != null)
			return "redirect:/success";
        return "home";
    }
	
	@GetMapping("/success")
	public String home(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		Credentials credentials = credentialsService.findByUsername(userDetails.getUsername());
		User user = credentials.getUser();
		switch (credentials.getRole()) {
		case ADMIN:
			return "home-admin";
		case DOCTOR:
			model.addAttribute("doctor", doctorService.findByUser(user));
			return "home-doctor";
		case PATIENT:
			model.addAttribute("patient", patientService.findByUser(user));
			return "home-patient";
		}
	    return "home";
	}
}
