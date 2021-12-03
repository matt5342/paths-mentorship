package com.pathsmentorship.pathsbackend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="admission_keys")
public class AdmissionKey {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	

	@NotNull
	private String name;
	
	@NotNull
	private String schoolName;
	
	
	public AdmissionKey() {}

	
	public AdmissionKey(@NotNull String name, @NotNull String schoolName) {
		this.name = name;
		this.schoolName = schoolName;
	}


	public AdmissionKey(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
}
