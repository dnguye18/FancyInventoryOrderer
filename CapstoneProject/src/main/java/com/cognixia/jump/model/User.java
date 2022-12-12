package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static enum Role {
		ROLE_USER, ROLE_MANAGER, ROLE_ADMIN
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Column( nullable = false )
	private String email;
	
	@NotBlank
	@Column( nullable = false )
	private String password;
	
	@Enumerated( EnumType.STRING )
	@Column( nullable = false )
	private Role role;
	
	@NotBlank
	@Column( unique = false, nullable = false )
	private String first_name;
	
	@NotBlank
	@Column( unique = false, nullable = false )
	private String last_name;
	
	@Column( unique = true, nullable = true )
	private String phone;
	
	@Column( columnDefinition = "boolean default true" )
	private boolean enabled;
	
	public User() {
		
	}

	public User(Integer id, @NotBlank String email, @NotBlank String password, Role role, @NotBlank String first_name,
			@NotBlank String last_name, String phone, boolean enabled) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone = phone;
		this.enabled = enabled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + ", first_name="
				+ first_name + ", last_name=" + last_name + ", phone=" + phone + ", enabled=" + enabled + "]";
	}
}
