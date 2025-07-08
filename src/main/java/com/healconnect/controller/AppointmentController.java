package com.healconnect.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.healconnect.model.Appointment;
import com.healconnect.model.Credentials;
import com.healconnect.model.Doctor;
import com.healconnect.model.Patient;
import com.healconnect.model.Role;
import com.healconnect.model.User;
import com.healconnect.service.AppointmentService;
import com.healconnect.service.CredentialsService;
import com.healconnect.service.DoctorService;
import com.healconnect.service.PatientService;
import com.healconnect.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/appointment")
public class AppointmentController extends GenericController<Appointment> {


	@Autowired
	private PatientService patientService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CredentialsService credentialsService;
	
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
		model.addAttribute("numero", doctorService.findById(id).getAppointments().size());
		return "appointment/list";
	}
	
	@GetMapping("/user/{id}/new")
	public String showCreateForm(@PathVariable Long id, Model model) {
		Appointment item = new Appointment();
		item.setBookingDate(LocalDate.now());
		User user = userService.findById(id);
		if (user.getCredentials().getRole() == Role.DOCTOR) {
			model.addAttribute("doctors", new Doctor[] {doctorService.findByUser(user)});
			model.addAttribute("patients", patientService.findAll());
		}
		if (user.getCredentials().getRole() == Role.PATIENT) {
			model.addAttribute("patients", new Patient[] {patientService.findByUser(user)});
			model.addAttribute("doctors", doctorService.findAll());
		}
		model.addAttribute("item", item);
		return "appointment/create";
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
	public String delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
		Credentials credenziali= credentialsService.findByUsername(userDetails.getUsername());
		User user=  credenziali.getUser();
		Doctor doctor= doctorService.findByUser(user);
		Appointment appointment= super.service.findById(id);
		if(appointment.getDoctor().equals(doctor))	{
			return super.delete(id, userDetails);
		}
		return "redirect:/appointment/doctor/" + doctor.getId();
		
	}
}
