package org.tothought.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tothought.entities.Post;
import org.tothought.repositories.PostPartRepository;
import org.tothought.repositories.PostRepository;

@Controller
public class HomeController {

	@Autowired
	PostRepository repository;

	PostPartRepository repository2;

	@RequestMapping("/")
	public String home(Model model) {

		List<Post> posts = repository.findAll();

		if (posts.size() == 0) {
			Post post = new Post();
			post.setAuthor("Kevin Bowersox");
			post.setPostedDt(new Date());
			repository.save(post);
		}
		
		posts = repository.findAll();
		
		model.addAttribute("post", posts.get(0));
		return "home";
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
