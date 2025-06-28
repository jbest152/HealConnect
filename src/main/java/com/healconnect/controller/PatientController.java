package com.healconnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healconnect.model.Patient;

@Controller
@RequestMapping("/patient")
public class PatientController extends GenericController<Patient>{

	public PatientController() {
		super(Patient.class);
	}
}
