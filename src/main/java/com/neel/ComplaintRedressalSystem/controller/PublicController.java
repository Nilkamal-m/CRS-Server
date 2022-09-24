package com.neel.ComplaintRedressalSystem.controller;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neel.ComplaintRedressalSystem.entity.User;
import com.neel.ComplaintRedressalSystem.entity.UserRequest;
import com.neel.ComplaintRedressalSystem.entity.UserResponse;
import com.neel.ComplaintRedressalSystem.repo.UserRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/test")
public class PublicController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
//	
	@PostMapping("/")
	public ResponseEntity<?> test(@RequestBody UserRequest userRequest){
		User user = new User();
		user.setUsername(userRequest.getUsername());
		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		user.setRole(userRequest.getRole());

		Optional<User> u =userRepository.findByUsername(userRequest.getUsername());
		if(u == null) {
			return new ResponseEntity<>("Username Already Exists",HttpStatus.CONFLICT);
		}else {
			userRepository.save(user);
			return new ResponseEntity<>("Registration successful",HttpStatus.CREATED);
		}
		
	}
//	
//
//	// ADD USER API END POINT
//
	@PostMapping("/signup")
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
		User user = new User();
		UserResponse userResponse = new UserResponse();
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole("ROLE_USER");

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
//	
//	@GetMapping("/test/{username}")
//	public Optional<User> test2(@PathVariable String username) {
//		Optional<User> user = userRepository.findByUsername(username);
//		return user;
//	}
//	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
//	
//	@ModelAttribute
//	public UserRequest userRequest() {
//		return new UserRequest();
//	}
//	
//	@GetMapping("/signup")
//	public String signup() {
//		return "signup";
//	}
//	
//	@PostMapping("/signup")
//	public String signup(@ModelAttribute("user") UserRequest userRequest) {
//		userRequest.setRole("ROLE_USER");
//		return "redirect:/signup?success";
//	}
	

	
	@PostMapping("/login")
	public ResponseEntity<UserResponse> welcomePage( @RequestBody UserRequest userRequest) {
		
		User user=userRepository.findByUsername(userRequest.getUsername()).get();
	
		UserResponse userResponse=new UserResponse();
		
		if (passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
			userResponse.setUsername(userRequest.getUsername());
			userResponse.setRole(Arrays.stream(user.getRole().split(",")).collect(Collectors.toList()));
			userResponse.setMsg("Login successful");
			return new ResponseEntity<>(userResponse,HttpStatus.OK);
		}
		userResponse.setMsg("Please provide the correct userid and password");
		return new ResponseEntity<>(userResponse,HttpStatus.NOT_FOUND);
	}

}
