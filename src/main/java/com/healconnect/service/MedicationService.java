package com.healconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healconnect.model.Medication;

import jakarta.transaction.Transactional;

@Service
public class MedicationService extends GenericService<Medication, Long> {

	@Autowired
	PrescriptionService prescriptionService;
	
	@Override
	@Transactional
	public void deleteById(Long id) {
		prescriptionService.deleteByMedicationId(id);
		super.deleteById(id);
	}
}
