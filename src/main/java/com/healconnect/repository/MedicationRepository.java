package com.healconnect.repository;

import org.springframework.data.repository.CrudRepository;

import com.healconnect.model.Medication;

public interface MedicationRepository extends CrudRepository<Medication, Long> {

}
