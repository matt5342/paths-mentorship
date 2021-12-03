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

import com.pathsmentorship.pathsbackend.models.Publication;
import com.pathsmentorship.pathsbackend.repository.PublicationRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PublicationController {
	
	@Autowired
	PublicationRepository publicationRepository;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/publications")
	public ResponseEntity<Publication> createPublication(@RequestBody Publication publication) {
		try {
			Publication _publication = publicationRepository
					.save(new Publication(publication.getTitle(),publication.getDescription(), publication.getLink()));
			return new ResponseEntity<>(_publication, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/publications")
	public ResponseEntity<List<Publication>> findAll(){
		
		List<Publication> publications = publicationRepository.findAll();
		try {
			if (publications.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(publications, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/publications/{id}")
	public ResponseEntity<HttpStatus> deletePublication(@PathVariable("id") long id){
		try {
			publicationRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
