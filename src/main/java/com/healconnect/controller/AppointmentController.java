package com.healconnect.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healconnect.model.Appointment;

@Controller
@RequestMapping("/appointment")
public class AppointmentController extends GenericController<Appointment> {

	public AppointmentController() {
		super(Appointment.class);
	}
	
	@Override
	@PreAuthorize("hasAuthority('PATIENT') or hasAuthority('DOCTOR')")
	@GetMapping("/new")
	public String showCreateForm(Model model){
		return super.showCreateForm(model);
	}
	
	@Override
	@PreAuthorize("hasAuthority('PATIENT') or hasAuthority('DOCTOR')")
	@GetMapping("/{id}/edit")
	public String showEditForm(@PathVariable Long id, Model model) {
		return super.showEditForm(id, model);
	}
	
	@Override
	@PreAuthorize("hasAuthority('DOCTOR')")
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		return super.delete(id);
	}
}
