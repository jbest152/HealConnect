package com.healconnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healconnect.model.Doctor;
import com.healconnect.service.DoctorService;

@Controller
@RequestMapping("/doctor")
public class DoctorController extends GenericController<Doctor> {

	public DoctorController(DoctorService service) {
		super(service, "doctor");
	}

	@Override
	protected Doctor getEntityInstance() {
		return new Doctor();
	}

}
