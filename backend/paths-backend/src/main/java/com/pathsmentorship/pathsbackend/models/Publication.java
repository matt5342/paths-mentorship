package com.pathsmentorship.pathsbackend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "publications")
public class Publication {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "title")
	private String title;

	@NotNull
	@Type(type = "text")
	@Column(name = "description")
	private String description;
	
	@NotNull
	@Type(type = "text")
	@Column(name = "link")
	private String link;
	

	
	public Publication() {}

	
	public Publication(@NotNull String title, @NotNull String description, @NotNull String link) {
		this.title = title;
		this.description = description;
		this.link = link;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	
}
