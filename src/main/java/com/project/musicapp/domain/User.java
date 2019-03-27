package com.project.musicapp.domain;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false )
	private Long id;
	
	@Column(name = "firstName", nullable = false) 
	private String firstName;
	
	@Column(name = "lastName", nullable = false) 
	private String lastName;
	
	@Column(name = "email", nullable = false) 
	private String email;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "password" , nullable = false) 
	private String password;

	@Column(name = "role", nullable = false) 
	private String role;
	
	public User() {}

	public User(String firstName, String lastName, String email, String username, String password, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String passwordHash) {
		this.password = passwordHash;
	}	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", username=" + username + ", password=" + password + ", role=" + role + "]";
	}
	
}
