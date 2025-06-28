package com.healconnect.repository;

import org.springframework.data.repository.CrudRepository;

import com.healconnect.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
