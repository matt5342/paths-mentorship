package com.pathsmentorship.pathsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pathsmentorship.pathsbackend.models.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long> {

}
