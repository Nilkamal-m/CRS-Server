package com.neel.ComplaintRedressalSystem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neel.ComplaintRedressalSystem.entity.Complain;

public interface ComplainRepository extends JpaRepository<Complain, Long> {

	List<Complain> findByUserId(int userId);

	Complain findTopByOrderByCidDesc();

	List<Complain> findByAssignEnggId(int userId);

}
