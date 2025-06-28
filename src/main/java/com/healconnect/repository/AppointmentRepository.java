package com.healconnect.repository;

import org.springframework.data.repository.CrudRepository;

import com.healconnect.model.Appointment;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

}
