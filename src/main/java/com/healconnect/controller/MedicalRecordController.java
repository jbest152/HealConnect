package com.healconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healconnect.service.MedicalRecordService;

@Controller
@RequestMapping("/medical-record")
public class MedicalRecordController{
	
	@Autowired
	private MedicalRecordService service;

	@GetMapping("/{id}")
	public String view(@PathVariable Long id, Model model) {

		model.addAttribute("medical-record", service.findById(id));
		return "medical-record" + "/view";
	}
}
