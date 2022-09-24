package com.neel.ComplaintRedressalSystem.entity;

import java.util.List;


public class UserResponse {

	private String username;
	private List<String> role;
	private String msg;
	
	
	public UserResponse() {
		super();
	}


	public UserResponse(String username, List<String> role, String msg) {
		super();
		this.username = username;
		this.role = role;
		this.msg = msg;
	}





	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRole() {
		return role;
	}


	public void setRole(List<String> role) {
		this.role = role;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
