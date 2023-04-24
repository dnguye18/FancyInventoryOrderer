package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static enum Role {
		ROLE_USER, ROLE_ADMIN
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Column( unique = true, nullable = false )
	private String username;
	
	@NotBlank
	@Column( nullable = false )
	private String password;
	
	@Enumerated( EnumType.STRING )
	@Column( nullable = false )
	private Role role;
	
	@NotBlank
	@Column( nullable = false )
	private String first_name;
	
	@NotBlank
	@Column( nullable = false )
	private String last_name;
	
	@Column( unique = true, nullable = true )
	private String phone;
	
	@Column( columnDefinition = "boolean default true" )
	private boolean enabled;
	
	@JsonManagedReference
	@OneToMany( mappedBy = "user", cascade = CascadeType.ALL )
	private List<Item> items;
	
	public User() {
		
	}

	public User(Integer id, @NotBlank String username, @NotBlank String password, Role role, @NotBlank String first_name,
			@NotBlank String last_name, String phone, boolean enabled) {
		super();
		this.id = id;
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
	
	public List<Item> getItems() {
		return items;
	}
	
	public void setOrders(List<Item> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		return Objects.hash(username, enabled, first_name, id, last_name, password, phone, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(username, other.username) && enabled == other.enabled
				&& Objects.equals(first_name, other.first_name) && Objects.equals(id, other.id)
				&& Objects.equals(last_name, other.last_name) && Objects.equals(password, other.password)
				&& Objects.equals(phone, other.phone) && role == other.role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + username + ", password=" + password + ", role=" + role + ", first_name="
				+ first_name + ", last_name=" + last_name + ", phone=" + phone + ", enabled=" + enabled + "]";
	}
}
