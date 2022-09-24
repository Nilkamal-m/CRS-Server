package com.neel.ComplaintRedressalSystem.controller;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neel.ComplaintRedressalSystem.entity.Complain;
import com.neel.ComplaintRedressalSystem.entity.User;
import com.neel.ComplaintRedressalSystem.entity.UserRequest;
import com.neel.ComplaintRedressalSystem.entity.UserResponse;
import com.neel.ComplaintRedressalSystem.repo.ComplainRepository;
import com.neel.ComplaintRedressalSystem.repo.UserRepository;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ComplainRepository complainRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// ADD USER API END POINT

	@PostMapping("/add-user")
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
		User user = new User();
		UserResponse userResponse = new UserResponse();
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole("ROLE_"+request.getRole());

		Optional<User> u = userRepository.findByUsername(request.getUsername());
		if (u!=null) {
			userRepository.save(user);
			userResponse.setUsername(user.getUsername());
			userResponse.setRole(Arrays.stream(user.getRole().split(",")).collect(Collectors.toList()));

			return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);

		}
	}

	// UPDATE USER BY ID

	@PutMapping("/user/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable int id, @RequestBody UserRequest request) {
		User user = userRepository.findById(id).get();

		Optional<User> u = userRepository.findByUsername(request.getUsername());

		if (u!=null) {
			user.setUsername(request.getUsername());
			user.setRole(request.getRole());
			userRepository.save(user);
			return ResponseEntity.ok().build();

		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

	}
	
	@GetMapping("/get-complains")
	public List<Complain> getComplains(){
		return complainRepository.findAll();
	}

	// VIEW ALL USER

	@GetMapping("/view-user")
	public List<User> findAllUser() {
		return userRepository.findAll();
	}

	// VIEW FIND USER BY USERNAME
	@GetMapping("/view-user/{username}")
	public Optional<User> findUserByUsername(@PathVariable String username) {
		return userRepository.findByUsername(username);
	}


	//delete user
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Object> removeUser(@PathVariable int id) {
		Optional<User> u = userRepository.findById(id);
		if (u!=null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			userRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
	}

}
