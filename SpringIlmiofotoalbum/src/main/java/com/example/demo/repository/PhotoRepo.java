package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Photo;

public interface PhotoRepo extends JpaRepository<Photo, Integer>{
	public List<Photo> findByTitleContainingOrTagContaining(String title, String tag);
}
