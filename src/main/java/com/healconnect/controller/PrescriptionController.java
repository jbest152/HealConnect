package com.healconnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healconnect.model.Prescription;

@Controller
@RequestMapping("/prescription")
public class PrescriptionController extends GenericController<Prescription>{

	public PrescriptionController() {
		super(Prescription.class);
	}
}
