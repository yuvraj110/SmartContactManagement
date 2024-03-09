package com.smartcontactmanager.entities;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message = "Name field is required !!")
	@Size(min = 2, max = 25, message = "min 2 and max 25")
	private String name;
	
	@Column(unique = true)
	@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$" , message = "Invalid Email")
	private String email;
	
	
	@NotBlank(message = "Password must be added")
	@Size(min = 4)
	private String password;
	
	@Column(length = 15)
	private String role;
	private boolean enabled;
	private String imageUrl;
	@Column(length = 500)
	private String about;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<ContactDetails> contacts;
	
	public List<ContactDetails> getContacts() {
		return contacts;
	}
	public void setContacts(List<ContactDetails> contacts) {
		this.contacts = contacts;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	public User() {
		super();
	}
	
	public User(int id, String name, String email, String password, String role, boolean enabled, String imageUrl,
			String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.enabled = enabled;
		this.imageUrl = imageUrl;
		this.about = about;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", enabled=" + enabled + ", imageUrl=" + imageUrl + ", about=" + about + ", contacts=" + contacts
				+ "]";
	}
	

}
