package com.ozguryazilim.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User {

	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	 private  Long id;
	 
	 @Column(unique=true)
	 private String email;
	  
	 private String name;
	  
	  
	 private String password;
	  
	  @ManyToMany(fetch=FetchType.EAGER)
	  @JoinTable(name="user_role",
	  joinColumns=@JoinColumn(name="user_id",referencedColumnName="id"),
	  inverseJoinColumns=@JoinColumn(name="role_id",referencedColumnName="id"))
	  private  List<Role> roles;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	  
	  
	  
}
