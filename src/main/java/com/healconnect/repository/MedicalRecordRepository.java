package com.healconnect.repository;

import org.springframework.data.repository.CrudRepository;

import com.healconnect.model.MedicalRecord;

public interface MedicalRecordRepository extends CrudRepository<MedicalRecord, Long> {

}
