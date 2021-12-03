package com.pathsmentorship.pathsbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pathsmentorship.pathsbackend.models.School;


public interface SchoolRepository extends JpaRepository<School, Long> {

	Optional<School> findByName(String name);
}
