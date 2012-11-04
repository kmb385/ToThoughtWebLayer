package org.tothought.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tothought.entities.TagView;
import org.tothought.repositories.PostViewRepository;
import org.tothought.repositories.TagViewRepository;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	PostViewRepository postViewRepository;

	@Autowired
	TagViewRepository tagViewRepository;
	
	@RequestMapping("/")
	public String blog(Model model) {
		Sort sort = new Sort(Direction.DESC, "postId");
		model.addAttribute("tags", this.findAllTags());
		model.addAttribute("posts",
				postViewRepository.findAll(new PageRequest(0, 5, sort))
						.getContent());
		model.addAttribute("tease", true);
		return "blog/blog";
	}

	@RequestMapping("/tag/{tagId}")
	public String getPostsByTag(@PathVariable Integer tagId, Model model){
		Sort sort = new Sort(Direction.DESC, "postId");
		
		model.addAttribute("tags", this.findAllTags());
		model.addAttribute("posts",
				postViewRepository.findByTag(tagId, new PageRequest(0, 5, sort))
						.getContent());
		model.addAttribute("tease", true);

		return "blog/blog";
	}
	
	/**
	 * Return all tags used for the blog.
	 * @return
	 */
	private List<TagView> findAllTags() {
		Sort sort = new Sort(Direction.ASC, "name");
		return tagViewRepository.findAll(sort);
	}
	
}
