package com.example.demo.controller;

import java.util.List;


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

import com.example.demo.model.Category;
import com.example.demo.model.Photo;
import com.example.demo.repository.Categoryrepo;
import com.example.demo.repository.PhotoRepo;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	@Autowired
	private PhotoRepo photorepo;
	@Autowired
	private Categoryrepo categoryrepo;
	
	@GetMapping
	public String index(Model model) {
		
		List<Category> categories = categoryrepo.findAll();
		categories.sort((id1, id2) -> id1.getId() - id2.getId());
		
		model.addAttribute("categories", categories);
		
		return "category/category-index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		
		Category category = new Category();
		List<Photo> photos = photorepo.findAll();
		
		model.addAttribute("category", category);
		model.addAttribute("photos", photos);
		
		return "category/category-create";
	}
	@PostMapping("/create")
	public String store(
			@Valid Category category, 
			BindingResult bindingResult, 
			RedirectAttributes redirectAttributes
		) {
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/categories/create";
		}
		
		categoryrepo.save(category);
		return "redirect:/categories";
	}
	@GetMapping("/edit/{id}")
	public String edit(
			@PathVariable("id") int id,
			Model model
		) {
		
		Category category= categoryrepo.findById(id).get();
		List<Photo> photos = photorepo.findAll();
		
		model.addAttribute("category", category);
		model.addAttribute("photos", photos);
		
		return "category/category-edit";
	}
	@PostMapping("/edit/{id}")
	public String update(
			@PathVariable("id") int id,
			@Valid @ModelAttribute Category formCategory,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			Model model
		) {
		
		if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/categories/edit/" + formCategory.getId();
		}
		
		categoryrepo.save(formCategory);
		
		return "redirect:/categories";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(
			@PathVariable("id") int id) {
		
		categoryrepo.deleteById(id);
		
		return "redirect:/categories";
	}
}
