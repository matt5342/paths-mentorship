package com.pathsmentorship.pathsbackend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="access_codes")
public class AccessCode {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	

	@NotNull
	private String name;
	
	@NotNull
	private String schoolName;
	
	@NotNull 
	private String roleName;
	
	public AccessCode() {}

	
	public AccessCode(@NotNull String name, @NotNull String schoolName, @NotNull String roleName) {
		this.name = name;
		this.schoolName = schoolName;
		this.roleName = roleName;
	}


	public AccessCode(String name) {
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
