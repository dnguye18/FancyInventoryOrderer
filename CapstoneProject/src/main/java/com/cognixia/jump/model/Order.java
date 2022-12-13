package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static enum Progress {
		COMPLETED, IN_PROGRESS, NOT_STARTED
	}
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	
	@ManyToOne
	@JoinColumn( name = "user", referencedColumnName="id")
	private User user;
	
	@Enumerated( EnumType.STRING )
	@Column( nullable = false )
	private Progress progress;
	
	public Order() {
		
	}

	public Order(Integer id, User user, Progress progress) {
		super();
		this.id = id;
		this.user = user;
		this.progress = progress;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Progress getProgress() {
		return progress;
	}

	public void setProgress(Progress progress) {
		this.progress = progress;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, progress, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id) && progress == other.progress && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", progress=" + progress + "]";
	}
}
