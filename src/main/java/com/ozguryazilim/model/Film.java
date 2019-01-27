package com.ozguryazilim.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name="film")
public class Film {

	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private  Long id;
	  
	  private String name;
	  
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private Date publicationDate;
	  
	  private String genre;
	  
	  private String description;
	  
	  private String media;
	  
	  
	  private  String language;
	  
	  @ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.MERGE)
	  @JoinTable(name="film_actor",
	    joinColumns=@JoinColumn(name="film_id",referencedColumnName="id"),
	    inverseJoinColumns=@JoinColumn(name="actor_id",referencedColumnName="id"))
	  private  List<Actor> actor;
	  

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

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<Actor> getActor() {
		return actor;
	}

	public void setActor(List<Actor> actor) {
		this.actor = actor;
	}
	  
	  
	  
	
	
}
