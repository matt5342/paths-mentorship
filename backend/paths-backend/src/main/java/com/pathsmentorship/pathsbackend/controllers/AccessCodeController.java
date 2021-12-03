package com.pathsmentorship.pathsbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pathsmentorship.pathsbackend.models.AccessCode;
import com.pathsmentorship.pathsbackend.repository.AccessCodeRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AccessCodeController {
	
	@Autowired
	AccessCodeRepository accessCodeRepository;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/accessCodes")
	public ResponseEntity<AccessCode> createAccessCode(@RequestBody AccessCode accessCode) {
		try {
			AccessCode _accessCode = accessCodeRepository
					.save(new AccessCode(accessCode.getName(), accessCode.getSchoolName(), accessCode.getRoleName()));
			return new ResponseEntity<>(_accessCode, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/accessCodes")
	public ResponseEntity<List<AccessCode>> findAll(){
		
		List<AccessCode> accessCodes = accessCodeRepository.findAll();
		try {
			if (accessCodes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(accessCodes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/accessCodes/{id}")
	public ResponseEntity<HttpStatus> deleteAccessCode(@PathVariable("id") long id){
		try {
			accessCodeRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
