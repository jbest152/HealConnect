package com.healconnect.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.healconnect.model.Patient;
import com.healconnect.model.User;

public interface PatientRepository extends CrudRepository<Patient, Long> {
	Optional<Patient> findByUser(User user);
}
