package com.pathsmentorship.pathsbackend.payload.response;

import java.util.List;
import java.util.Set;

import com.pathsmentorship.pathsbackend.models.School;

public class UsersResponse {

	private Long id;
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private String school;
	private Set<String> roles;

	public UsersResponse() {}
	public UsersResponse(Long id, String username, String email, 
						String firstName, String lastName, String school, Set<String> roles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.school = school;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	public Set<String> getRoles() {
		return roles;
	}
}