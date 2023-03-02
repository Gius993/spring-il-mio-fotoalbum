package com.example.demo.controller;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Photo;
import com.example.demo.repository.PhotoRepo;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/photos")
public class PhotoController {
	@Autowired
	private PhotoRepo photorepo;
	
	
	@GetMapping
	public String index(Model model) {
		
		List<Photo> photos = photorepo.findAll();
		model.addAttribute("photos", photos);
		
		return "photo/photo-index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		
		Photo photo = new Photo();	
		model.addAttribute("photo", photo);
		
		return "photo/photo-create";
	}
	@PostMapping("/create")
	public String store(
			@Valid Photo photo, 
			BindingResult bindingResult, 
			RedirectAttributes redirectAttributes
		) {
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/photos/create";
		}
		
		photorepo.save(photo);
		return "redirect:/photos";
	}
}
