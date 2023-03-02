package com.example.demo.controller;





import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping("/show/{id}")
	public String show(
			@PathVariable("id") int id,
			Model model) {
		
		Optional<Photo> optPhoto = photorepo.findById(id);
		
		Photo photo = optPhoto.get();
		
		model.addAttribute("photo", photo);
		
		return "photo/photo-show";
	}


	@GetMapping("/edit/{id}")
	public String edit(
			@PathVariable("id") int id,
			Model model
		) {
		
		Photo photo = photorepo.findById(id).get();
		
		model.addAttribute("photo", photo);
		
		return "photo/photo-edit";
	}
	
	@PostMapping("/edit")
	public String update(
			@Valid @ModelAttribute Photo formPhoto,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			Model model
		) {
		
		if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/photos/edit/" + formPhoto.getId();
		}
		photorepo.save(formPhoto);
		
		return "redirect:/photos";
	}
}
