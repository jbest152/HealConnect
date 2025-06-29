package com.healconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healconnect.model.Doctor;
import com.healconnect.model.User;
import com.healconnect.repository.DoctorRepository;

@Service
public class DoctorService extends GenericService<Doctor, Long>{

	@Autowired
	private DoctorRepository repository;
	
	public Doctor findByUser(User user) {
		return repository.findByUser(user).get();
	}
}
