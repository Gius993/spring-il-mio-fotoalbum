package com.example.demo.apicontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Comment;
import com.example.demo.model.Photo;
import com.example.demo.repository.Commentrepo;
import com.example.demo.repository.PhotoRepo;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/photos")
public class PhotoApiController {

	@Autowired
	private PhotoRepo photorepo;
	
	@Autowired
	private Commentrepo commentrepo;
	
	
	@GetMapping()		
	public List<Photo> index() {
		return photorepo.findAll();
	}
	
	@GetMapping("{id}")		
	public ResponseEntity<Photo> detail(@PathVariable("id") Integer id) {
		Optional<Photo> res=photorepo.findById(id);
		if (res.isPresent()) 
			return new ResponseEntity<Photo>(res.get(), HttpStatus.OK);
	    else
	    	return new ResponseEntity<Photo>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/comment/{id}")
	public Comment postComment(
	        @PathVariable("id") int id,
	        @Valid @RequestBody Comment comment
	) {
	    Optional<Photo> photoOpt = photorepo.findById(id);

	    if (photoOpt.isEmpty()) {
	      
	    }

	    Photo photo = photoOpt.get();
	    comment.setPhoto(photo);

	    return commentrepo.save(comment);
	}
}
