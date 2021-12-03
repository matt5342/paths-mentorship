package com.pathsmentorship.pathsbackend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pathsmentorship.pathsbackend.models.User;
import com.pathsmentorship.pathsbackend.payload.response.UsersResponse;
import com.pathsmentorship.pathsbackend.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/users")
	public ResponseEntity<List<UsersResponse>> findAll(){
		
		List<User> users = userRepository.findAll();
		List<UsersResponse> usersResponse = new ArrayList<>();
		users.forEach(user -> {
			UsersResponse _usersResponse = new UsersResponse();
			_usersResponse.setId(user.getId());
			_usersResponse.setUsername(user.getUsername());
			_usersResponse.setEmail(user.getEmail());
			_usersResponse.setFirstName(user.getFirstName());
			_usersResponse.setLastName(user.getLastName());
			_usersResponse.setSchool(user.getSchool().getName());
//			Do we need to know the roles?
//			_usersResponse.setRoles(user.getRoles());
			usersResponse.add(_usersResponse);

		});
		try {
			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(usersResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id){
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
