package com.healconnect.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.healconnect.model.Doctor;
import com.healconnect.model.User;

public interface DoctorRepository extends CrudRepository<Doctor, Long>{
	 Optional<Doctor> findByUser(User user);
}
