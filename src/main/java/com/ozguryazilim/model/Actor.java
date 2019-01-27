package com.ozguryazilim.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Actor {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private  Long id;
	  
	  private String name;
	  
	  private String surName;
	  
	  private String role;
	  
	  @JsonIgnore
	  @ManyToMany(mappedBy="actor",fetch=FetchType.LAZY)
	  private  List<Film> film;

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

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Film> getFilm() {
		return film;
	}

	public void setFilm(List<Film> film) {
		this.film = film;
	}
	  
	  
	  
	  

}
