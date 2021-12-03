package com.pathsmentorship.pathsbackend.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pathsmentorship.pathsbackend.models.School;
import com.pathsmentorship.pathsbackend.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String email;
	
	@JsonIgnore
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private School school;


	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String username, String email, String password, String firstName, String lastName,
			School school, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.school = school;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

//		System.out.println("UserDetailsImpl build username: " + user.getUsername());
//		System.out.println("UserDetailsImpl build user pw: " + user.getPassword());
//		System.out.println("UserDetailsImpl build user firstName: " + user.getFirstName());
//		System.out.println("UserDetailsImpl build user school: " + user.getSchool());
//		System.out.println("UserDetailsImpl build user lastName: " + user.getLastName());
		
		return new UserDetailsImpl(
				user.getId(), 
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(), 
				user.getFirstName(),
				user.getLastName(),
				user.getSchool(),
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
//		System.out.println("In userDetailsImpl getPassword");
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
//		System.out.println("In userDetailsImpl getLastName");
		return lastName;
	}
	
	public School getSchool() {
		return school;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
//		System.out.println("In UserDetailsImpl equals method");
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}


}