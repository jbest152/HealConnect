package com.healconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healconnect.service.MedicalRecordService;
import com.healconnect.service.PatientService;

@Controller
@RequestMapping("/medical-record")
public class MedicalRecordController{
	
	@Autowired
	private MedicalRecordService service;
	
	@Autowired
	private PatientService patientService;

	@GetMapping("/{id}")
	public String view(@PathVariable Long id, Model model) {

		model.addAttribute("medical-record", service.findById(id));
		return "medical-record" + "/view";
	}
	
	@GetMapping("/patient/{id}")
	public String viewByPatient(@PathVariable Long id, Model model) {

		model.addAttribute("medical-record", patientService.findById(id).getMedicalRecord());
		return "medical-record" + "/view";
	}
}
