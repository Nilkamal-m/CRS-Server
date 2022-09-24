package com.neel.ComplaintRedressalSystem.controller;


import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neel.ComplaintRedressalSystem.entity.Complain;
import com.neel.ComplaintRedressalSystem.entity.User;
import com.neel.ComplaintRedressalSystem.repo.ComplainRepository;
import com.neel.ComplaintRedressalSystem.repo.UserRepository;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
	
	
	
	@Autowired
	private ComplainRepository complainRepository;
	
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("/add-complain")
	public long addComplain(@RequestBody Complain complain,Principal principal) {
		Optional<User> user = userRepo.findByUsername(getName(principal));
		Complain comp = new Complain();
		comp.setComplain(complain.getComplain());
		comp.setStatus("ESCALATED");
		comp.setUserId(user.get().getId());
		complainRepository.save(comp);
		Complain compId=complainRepository.findTopByOrderByCidDesc();
		return compId.getCid();
	}
	
	
	@GetMapping("/get-complains")
	public List<Complain> getComplains(Principal principal){
		Optional<User> user = userRepo.findByUsername(getName(principal));
		int userId=user.get().getId();
		
		return complainRepository.findByUserId(userId);
	}
	
	@GetMapping("/get-complain/{cid}")
	public Complain getComplainById(@PathVariable long cid) {
		return complainRepository.findById(cid).get();
	}

	private String getName(Principal principal) {
		return principal.getName();
	}
}
