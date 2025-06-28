package com.healconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.healconnect.model.Credentials;
import com.healconnect.repository.CredentialsRepository;

public class CredentialsService extends GenericService<Credentials, Long> {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CredentialsRepository repository;
	
	@Override
	public Credentials save(Credentials credentials) {
		credentials.setPasswordHash(passwordEncoder.encode(credentials.getPasswordHash()));
		return repository.save(credentials);
	}
	
    public Credentials findByUsername(String username) {
        return repository.findByUsername(username).get();
    }
}
