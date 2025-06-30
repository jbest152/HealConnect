package com.healconnect.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.healconnect.model.Appointment;
import com.healconnect.service.DoctorService;
import com.healconnect.service.PatientService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/appointment")
public class AppointmentController extends GenericController<Appointment> {


	@Autowired
	private PatientService patientService;
	
	@Autowired
	private DoctorService doctorService;
	
	public AppointmentController() {
		super(Appointment.class);
	}
	
	@GetMapping("/patient/{id}")
	public String listFromPatient(@PathVariable Long id, Model model) {
		model.addAttribute("appointments", patientService.findById(id).getAppointments());
		model.addAttribute("user", patientService.findById(id).getUser());
		return "appointment/list";
	}
	
	@GetMapping("/doctor/{id}")
	public String listFromDoctor(@PathVariable Long id, Model model) {
		model.addAttribute("appointments", doctorService.findById(id).getAppointments());
		model.addAttribute("user", doctorService.findById(id).getUser());
		return "appointment/list";
	}
	
	@GetMapping("/patient/{id}/new")
	public String showCreateForm(@PathVariable Long id, Model model) {
		Appointment item = new Appointment();
		item.setPatient(patientService.findById(id));
		model.addAttribute("item", item);
		return "appointment/list";
	}

	@Override
	@PreAuthorize("hasAuthority('PATIENT') or hasAuthority('DOCTOR')")
	@GetMapping("/new")
	public String showCreateForm(Model model){
		Appointment item = new Appointment();
		item.setBookingDate(LocalDate.now());
		model.addAttribute("patients", patientService.findAll());
		model.addAttribute("doctors", doctorService.findAll());
		model.addAttribute("item", item);
		return "appointment/create";
	}
	

	@Override
	@PostMapping
	public String create(@Valid @ModelAttribute("item") Appointment item, BindingResult result) {
		if (result.hasErrors()) {
			return "appointment/create";
		}
		service.save(item);
		return "redirect:/appointment/"  + item.getId();
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
