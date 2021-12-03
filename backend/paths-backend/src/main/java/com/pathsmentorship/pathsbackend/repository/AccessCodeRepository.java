package com.pathsmentorship.pathsbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pathsmentorship.pathsbackend.models.AccessCode;



public interface AccessCodeRepository extends JpaRepository<AccessCode, Long> {

	Optional<AccessCode> findByName(String name);
	
	Boolean existsByName(String name);
}
