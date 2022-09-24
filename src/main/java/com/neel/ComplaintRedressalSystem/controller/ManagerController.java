package com.neel.ComplaintRedressalSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neel.ComplaintRedressalSystem.entity.Complain;
import com.neel.ComplaintRedressalSystem.entity.User;
import com.neel.ComplaintRedressalSystem.repo.ComplainRepository;
import com.neel.ComplaintRedressalSystem.repo.UserRepository;

@RestController
@RequestMapping("/manager")
@CrossOrigin(origins = "*") 
public class ManagerController {
	
	@Autowired
	private ComplainRepository complainRepository;
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/view-all-complain")
	public List<Complain> getAllComplain(){
		return complainRepository.findAll();
	}
	
	@PutMapping("/assign-engg/{cid}")
	public void assignEngg(@PathVariable long cid,@RequestBody Complain complain) {
		
		Complain comp=complainRepository.findById(cid).get();
		comp.setAssignEnggId(complain.getAssignEnggId());
		complainRepository.save(comp);
	}
	
	@GetMapping("/get-all-engg")
	public List<User> getAllEngg(){
		return userRepo.findByRole("ROLE_ENGG");
	}
	
}
