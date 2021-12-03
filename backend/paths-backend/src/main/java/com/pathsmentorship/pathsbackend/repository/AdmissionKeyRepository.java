package com.pathsmentorship.pathsbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pathsmentorship.pathsbackend.models.AdmissionKey;

public interface AdmissionKeyRepository extends JpaRepository<AdmissionKey, Long> {

	Optional<AdmissionKey> findByName(String name);
	
	Boolean existsByName(String name);
}
