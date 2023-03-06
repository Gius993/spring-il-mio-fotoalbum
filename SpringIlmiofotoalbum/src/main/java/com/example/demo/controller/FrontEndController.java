package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service")
public class FrontEndController {
	@GetMapping
	public String index() {
		return "fotoalbumfront/index";
	}
	@GetMapping("{id}")
	public String show (){
		return "fotoalbumfront/detail";
	}
}
