package com.neel.ComplaintRedressalSystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Complain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cid;
	private String complain;
	private String status;
	private int assignEnggId;
	private int userId;
	
	public Complain() {
		super();
	}

	

	public Complain(Long cid, String complain, String status, int assignEnggId) {
		super();
		this.cid = cid;
		this.complain = complain;
		this.status = status;
		this.assignEnggId = assignEnggId;
	}


	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getComplain() {
		return complain;
	}

	public void setComplain(String complain) {
		this.complain = complain;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public int getAssignEnggId() {
		return assignEnggId;
	}

	public void setAssignEnggId(int assignEnggId) {
		this.assignEnggId = assignEnggId;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	
}
