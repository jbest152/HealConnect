package com.healconnect.service;
import com.healconnect.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import com.healconnect.model.Prescription;

@Service
public class PrescriptionService extends GenericService<Prescription, Long> {

	public void deleteByMedicationId(Long id) {
		PrescriptionRepository repository = (PrescriptionRepository) super.repository;
		repository.deleteByMedicationId(id);
	}
}
