package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Orders implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static enum Progress {
		COMPLETED, IN_PROGRESS, NOT_STARTED
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private Progress progress;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn( name = "user_id", referencedColumnName = "id" )
	private User usr;
	
	@JsonManagedReference
	@OneToMany( mappedBy = "order", cascade = CascadeType.ALL)
	private List<Item> items;

	public Orders() {
		
	}
	
	public Orders(Integer id, Progress progress, User usr) {
		super();
		this.id = id;
		this.progress = progress;
		this.usr = usr;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Progress getProgress() {
		return progress;
	}

	public void setProgress(Progress progress) {
		this.progress = progress;
	}

	public User getUsr() {
		return usr;
	}

	public void setUsr(User usr) {
		this.usr = usr;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	@Override
	public String toString() {
		return "Orders [id=" + id + ", progress=" + progress + ", usr=" + usr + ", items=" + items + "]";
	}
}