package com.healconnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healconnect.model.Medication;

@Controller
@RequestMapping("/medication")
public class MedicationController extends GenericController<Medication> {

	public MedicationController() {
		super(Medication.class);
	}
}
