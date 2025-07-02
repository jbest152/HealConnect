package com.healconnect.repository;

import org.springframework.data.repository.CrudRepository;

import com.healconnect.model.Prescription;

public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {

	void deleteByMedicationId(Long medicationId);
}
