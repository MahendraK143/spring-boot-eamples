package com.zuul.employeezuulapigatewayservice.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtEmployeeDetailsService implements UserDetailsService{
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if (username == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		} 
		return new org.springframework.security.core.userdetails.User(username,bcryptEncoder.encode("password"),
				new ArrayList<>());
	}
	
}
