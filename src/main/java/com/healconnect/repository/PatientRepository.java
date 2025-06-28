package com.healconnect.repository;

import org.springframework.data.repository.CrudRepository;

import com.healconnect.model.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long> {

}
