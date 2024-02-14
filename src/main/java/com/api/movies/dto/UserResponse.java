package com.api.movies.dto;

public class UserResponse {

	private String firstName;
	private String email;

	public UserResponse(String firstName, String email) {
		super();
		this.firstName = firstName;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
