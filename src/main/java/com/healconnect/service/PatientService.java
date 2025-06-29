package com.healconnect.service;
import com.healconnect.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healconnect.model.Patient;
import com.healconnect.model.User;

@Service
public class PatientService extends GenericService<Patient, Long> {

	@Autowired
    private PatientRepository repository;

	public Patient findByUser(User user) {
		return repository.findByUser(user).get();
	}
}
