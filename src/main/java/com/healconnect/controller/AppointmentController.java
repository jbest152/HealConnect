package com.healconnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healconnect.model.Appointment;

@Controller
@RequestMapping("/appointment")
public class AppointmentController extends GenericController<Appointment> {

	public AppointmentController() {
		super(Appointment.class);
	}
}
