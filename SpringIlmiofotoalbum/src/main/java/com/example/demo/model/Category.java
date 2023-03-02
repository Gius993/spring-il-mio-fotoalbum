package com.example.demo.model;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Deve esserci un titolo")
	@Column(unique = true)
	private String name;
	
	@ManyToMany(mappedBy = "categories")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Photo> photos;
	

	

	
	
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
	
	
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	
	public void addPhoto(Photo photo) {
		boolean finded = false;
		
		for (Photo p : getPhotos()) {
			
			if (p.getId() == photo.getId()) finded = true;
			
			if(!finded)
			getPhotos().add(photo);
		}
	}
}
