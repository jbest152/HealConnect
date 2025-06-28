package com.healconnect.repository;

import org.springframework.data.repository.CrudRepository;

import com.healconnect.model.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Long>{
	
}
