package org.tothought.spring.controllers.blog;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tothought.entities.Post;
import org.tothought.entities.PostPart;
import org.tothought.repositories.PostRepository;
import org.tothought.repositories.TagRepository;
import org.tothought.spring.utilities.TagCreatorUtil;
import org.tothought.validators.PostValidator;

@Controller
@RequestMapping("/secure/post")
public class SecurePostController {
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	TagRepository tagRepository;

	@Autowired
	TagCreatorUtil tagCreatorUtil;

	@Secured("ROLE_ADMIN")
	@RequestMapping("/new")
	public String createPost(Model model) {
		Post post = new Post();
		post.setPostPart(new PostPart());
		model.addAttribute("post", post);
		//model.addAttribute("postPart", new PostPart());
		return "blog/managePost";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/edit/{postId}")
	public String editPost(@PathVariable Integer postId, Model model) {
		model.addAttribute("post", postRepository.findOne(postId));
		return "blog/managePost";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/save")
	public String savePost(@Valid @ModelAttribute Post post, BindingResult result, @RequestParam("tags") String tags) {
		
		if(result.hasErrors()){
			return "blog/managePost";
		}

		post.setAuthor("Kevin Bowersox");
		post.setPostedDt(new Date());
		post.setTags(tagCreatorUtil.createTags(tags));
		
		postRepository.save(post);
		
		return "redirect:/blog/";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/delete/{postId}")
	public String deletePost(@PathVariable Integer postId, Model model) {
		postRepository.delete(postId);
		return "redirect:/blog/";
	}

	/**
	 * Sets a binder to handle the conversion of the file.
	 * 
	 * @param binder
	 */
	@InitBinder("post")
	public void initBinderAll(WebDataBinder binder) {
		binder.setValidator(new PostValidator());
	}

}
