package com.healconnect.repository;

import org.springframework.data.repository.CrudRepository;

import com.healconnect.model.Image;

public interface ImageRepository extends CrudRepository<Image, Long> {
	
}
