package com.pathsmentorship.pathsbackend.payload.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.pathsmentorship.pathsbackend.models.School;

public class SignupRequest {

	@NotBlank
	@Size(min = 3, max = 20)
	private String username;
	
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	
//	private Set<String> role;
	
	@Size(max = 30)
	@NotBlank
	private String firstName;
	
	@Size(max = 30)
	@NotBlank
	private String lastName;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;
	
	@NotBlank
	private String accessCode;
	
//	private String school;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public Set<String> getRole() {
//		return role;
//	}
//
//	public void setRole(Set<String> role) {
//		this.role = role;
//	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

//	public String getSchool() {
//		return school;
//	}
//
//	public void setSchool(String school) {
//		this.school = school;
//	}
	
	
}
