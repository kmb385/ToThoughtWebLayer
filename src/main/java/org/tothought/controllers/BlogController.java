package org.tothought.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tothought.entities.Post;
import org.tothought.entities.PostPart;
import org.tothought.entities.Tag;
import org.tothought.repositories.PostRepository;
import org.tothought.repositories.PostViewRepository;
import org.tothought.repositories.TagRepository;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	PostRepository postRepository;

	@Autowired
	TagRepository tagRepository;

	@Autowired
	PostViewRepository postViewRepository;

	@RequestMapping("/")
	public String blog(Model model) {
		Sort sort = new Sort(Direction.DESC, "postId");
		model.addAttribute("posts",
				postViewRepository.findAll(new PageRequest(0, 5, sort))
						.getContent());
		model.addAttribute("tease", true);
		return "blog/blog";
	}
	
}
