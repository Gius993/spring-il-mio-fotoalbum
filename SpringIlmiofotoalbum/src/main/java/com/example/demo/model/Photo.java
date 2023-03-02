package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Photo {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	
	
	@Column(unique = true)
	private String title;
	

	@Column

	private String description;
	
	@Column
	private String tag;
	
	
	@Column
	private String url;
	
	
	@Column
	private boolean visible;
	@ManyToMany
	private List<Category> categories;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public void addCategory(Category category) {
		boolean finded = false;
		
		for (Category c : getCategories()) {
			
			if (c.getId() == category.getId()) finded = true;
			
			if(!finded)
			getCategories().add(category);
		}
	}
}
