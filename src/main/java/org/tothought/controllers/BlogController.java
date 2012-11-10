package org.tothought.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tothought.controllers.annotations.PageableRequestMapping;
import org.tothought.entities.PostView;
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
	
	private Sort sort = new Sort(Direction.DESC, "postId");	
	private int pageSize = 5;
	
	@RequestMapping("/page/{page}")
	@PageableRequestMapping(pathVariable="page")
	public String getBlogPage(Model model, @PathVariable int page) {
		this.setModelAttributes(model, page);
		return "blog/blog";
	}

	@RequestMapping("/tag/{tagId}/page/{pageNumber}")
	@PageableRequestMapping(pathVariable="page")
	public String getPostPageByTag(@PathVariable Integer tagId, @PathVariable int pageNumber, Model model){
		Page<PostView> page = this.getPageByTag(tagId, pageNumber);
		this.setModelAttributes(model, page);
		return "blog/blog";
	}
	
	/**
	 * Set the common model attributes for the request.
	 * @param model
	 * @param pageNumber
	 */
	private void setModelAttributes(Model model, int pageNumber) {
		Page<PostView> page = this.getPage(pageNumber);
		this.setModelAttributes(model, page);
	}
	
	/**
	 * Set common model attributes for the request
	 * @param model
	 * @param page
	 */
	private void setModelAttributes(Model model, Page<PostView> page){
		model.addAttribute("tags", this.findAllTags());
		model.addAttribute("posts", page.getContent());
		model.addAttribute("tease", true);
		model.addAttribute("lastPage", page.isLastPage());
	}

	/**
	 * Returns a List<PostView> corresponding with the specified page.
	 * @param pageNumber
	 * @return
	 */
	private Page<PostView> getPage(int pageNumber){
		PageRequest pageRequest = new PageRequest(pageNumber, this.pageSize, this.sort);
		return postViewRepository.findAll(pageRequest);
	}
	
	private Page<PostView> getPageByTag(int tagId, int pageNumber){
		PageRequest pageRequest = new PageRequest(pageNumber, this.pageSize, this.sort);
		return postViewRepository.findByTag(tagId, pageRequest);
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
