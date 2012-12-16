package org.tothought.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tothought.repositories.PostPartRepository;
import org.tothought.repositories.PostRepository;

@Controller
public class HomeController {

	@Autowired
	PostRepository repository;

	PostPartRepository repository2;

	@RequestMapping("/")
	public String home(Model model) {
		return "redirect:/blog/";
	}
	
	@RequestMapping("/save")
	public String save(@RequestParam String content, Model model){
		System.out.println(content);
		model.addAttribute("myContent", content);
		return "home";
	}
	
	@RequestMapping("/mce")
	public String loadTinyMceTest(){
		return "syntax";
	}

}
