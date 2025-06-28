package com.healconnect.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healconnect.model.Prescription;

@Controller
@RequestMapping("/prescription")
public class PrescriptionController extends GenericController<Prescription>{

	public PrescriptionController() {
		super(Prescription.class);
	}
	
	@Override
	@PreAuthorize("hasAuthority('DOCTOR')")
	@GetMapping("/new")
	public String showCreateForm(Model model){
		return super.showCreateForm(model);
	}
	
	@Override
	@PreAuthorize("hasAuthority('DOCTOR')")
	@GetMapping("/{id}/edit")
	public String showEditForm(@PathVariable Long id, Model model) {
		return super.showEditForm(id, model);
	}
}
