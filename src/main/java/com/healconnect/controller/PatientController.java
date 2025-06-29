package com.healconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healconnect.model.MedicalRecord;
import com.healconnect.model.Patient;
import com.healconnect.service.UserService;

@Controller
@RequestMapping("/patient")
public class PatientController extends GenericController<Patient>{

	@Autowired
	UserService userService;
	
	public PatientController() {
		super(Patient.class);
	}
	
	@GetMapping("/complete-registration/{userId}")
	public String registerPatient(@PathVariable Long userId) {
		MedicalRecord medRecord = new MedicalRecord();
		Patient patient = new Patient();
		patient.setMedicalRecord(medRecord);
		medRecord.setPatient(patient);
		patient.setUser(userService.findById(userId));
		super.service.save(patient);
		return "redirect:/login";
	}
}
