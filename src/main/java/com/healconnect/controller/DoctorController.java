package com.healconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healconnect.model.Doctor;
import com.healconnect.model.Role;
import com.healconnect.service.UserService;

@Controller
@RequestMapping("/doctor")
public class DoctorController extends GenericController<Doctor> {

	@Autowired
	UserService userService;
	
	public DoctorController() {
		super(Doctor.class);
	}
	
	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/new")
	public String showCreateForm(Model model){
		return "redirect:/register/" + Role.DOCTOR;
	}
	
	@GetMapping("/complete-registration/{userId}")
	public String registerDoctor(@PathVariable Long userId, Model model) {
		Doctor doctor = new Doctor();
		doctor.setUser(userService.findById(userId));
		model.addAttribute("item", doctor);
		return "doctor/create";
	}
}
