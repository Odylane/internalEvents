package fr.formation.internalevents.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class UserCreateDto {
	
	@NotBlank
	@Length(max=12)
	private String username;
	
	@NotBlank
	@Length(max=100)
	private String firstname;
	
	@NotBlank
	@Length(max=100)
	private String lastname;
	
	@NotBlank
	@Length(min= 8, max=12, message = "Le mot de passe doit contenir entre 8 et 12 caractères.")
	private String password;
	
	
	public UserCreateDto() {
		//
	}
	
	
		public String getUsername() {
		return username;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getPassword() {
		return password;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
