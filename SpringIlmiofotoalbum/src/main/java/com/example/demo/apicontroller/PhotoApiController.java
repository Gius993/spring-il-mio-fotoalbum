package com.example.demo.apicontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Photo;
import com.example.demo.repository.PhotoRepo;

@RestController
@RequestMapping("/api/1/photos")
@CrossOrigin("*")
public class PhotoApiController {

	@Autowired
	private PhotoRepo photorepo;
	@GetMapping("/all")
	public List<Photo> getAll() {
		
		return photorepo.findAll();
	}
	
	
}
