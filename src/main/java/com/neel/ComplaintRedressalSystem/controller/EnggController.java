package com.neel.ComplaintRedressalSystem.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neel.ComplaintRedressalSystem.entity.Complain;
import com.neel.ComplaintRedressalSystem.entity.User;
import com.neel.ComplaintRedressalSystem.repo.ComplainRepository;
import com.neel.ComplaintRedressalSystem.repo.UserRepository;

@RestController
@RequestMapping("/engg")
@CrossOrigin(origins = "*")
public class EnggController {

	@Autowired
	private ComplainRepository complainRepository;
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/view-complains")
	public List<Complain> getAssignComplain(Principal principal){
		Optional<User> user = userRepo.findByUsername(getName(principal));
		int userId=user.get().getId();
		return complainRepository.findByAssignEnggId(userId);
	}
	
	@PutMapping("/update-status/{cid}")
	public void updateComplainStatus(@PathVariable long cid ,@RequestBody Complain complain) {
		Complain comp=complainRepository.findById(cid).get();
		comp.setStatus(complain.getStatus());
		complainRepository.save(comp);
	}
	private String getName(Principal principal) {
		return principal.getName();
	}
	

}
