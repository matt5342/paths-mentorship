package com.pathsmentorship.pathsbackend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(length=20)
	private ERole name;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "access_code_id")
//	private AccessCode accessCode;
	
	public Role() {}

	public Role(ERole name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}

//	public AccessCode getAccessCode() {
//		return accessCode;
//	}
//
//	public void setAccessCode(AccessCode accessCode) {
//		this.accessCode = accessCode;
//	}
	
	
	
}
