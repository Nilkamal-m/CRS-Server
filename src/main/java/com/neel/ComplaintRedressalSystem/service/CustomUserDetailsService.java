package com.neel.ComplaintRedressalSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.neel.ComplaintRedressalSystem.entity.User;
import com.neel.ComplaintRedressalSystem.repo.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = userRepo.findByUsername(username);
		
		return user.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException(username+" is not found"));
	}

}
