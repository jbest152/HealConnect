package com.healconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.healconnect.model.Credentials;
import com.healconnect.repository.CredentialsRepository;

@Service
public class CredentialsService extends GenericService<Credentials, Long> {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CredentialsRepository repository;
	
	@Override
	public Credentials save(Credentials credentials) {
		credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
		return repository.save(credentials);
	}
	
    public Credentials findByUsername(String username) {
        return repository.findByUsername(username).get();
    }
}
