package com.healconnect.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healconnect.model.Doctor;
import com.healconnect.model.Patient;
import com.healconnect.model.Prescription;
import com.healconnect.model.User;
import com.healconnect.service.DoctorService;
import com.healconnect.service.MedicationService;
import com.healconnect.service.PatientService;
import com.healconnect.service.UserService;

@Controller
@RequestMapping("/prescription")
public class PrescriptionController extends GenericController<Prescription>{

	@Autowired
	private MedicationService medicationService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PatientService patientService;
	
	public PrescriptionController() {
		super(Prescription.class);
	}
	
	@Override
	@PreAuthorize("hasAuthority('DOCTOR')")
	@GetMapping("/new")
	public String showCreateForm(Model model){
		return super.showCreateForm(model);
	}
	
	@GetMapping("/patient/{id}")
	public String listFromPatient(@PathVariable Long id, Model model) {
		Patient patient = patientService.findById(id);
		model.addAttribute("prescriptions", patient.getPrescriptions());
		model.addAttribute("user", patient.getUser());
		return "prescription/list";
	}
	
	@GetMapping("/doctor/{id}")
	public String listFromDoctor(@PathVariable Long id, Model model) {
		Doctor doctor = doctorService.findById(id);
		model.addAttribute("prescriptions", doctor.getPrescriptions());
		model.addAttribute("user", doctor.getUser());
		return "prescription/list";
	}
	
	@PreAuthorize("hasAuthority('DOCTOR')")
	@GetMapping("/user/{id}/new")
	public String showCreateForm(@PathVariable Long id, Model model){
		Prescription item = new Prescription();
		User user = userService.findById(id);
		Doctor doctor = doctorService.findByUser(user);
		item.setDoctor(doctor);
		item.setDate(LocalDate.now());
		model.addAttribute("item", item);
		model.addAttribute("medications", medicationService.findAll());
		model.addAttribute("doctors",new Doctor[] {doctor});
		model.addAttribute("patients", patientService.findAll());
		return "prescription/create";
	}
	
	@Override
	@PreAuthorize("hasAuthority('DOCTOR')")
	@GetMapping("/{id}/edit")
	public String showEditForm(@PathVariable Long id, Model model) {
		return super.showEditForm(id, model);
	}
}
