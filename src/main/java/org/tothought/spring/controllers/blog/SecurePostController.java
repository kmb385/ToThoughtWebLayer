package org.tothought.spring.controllers.blog;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tothought.entities.Post;
import org.tothought.entities.PostPart;
import org.tothought.repositories.PostRepository;
import org.tothought.repositories.TagRepository;
import org.tothought.spring.utilities.TagCreatorUtil;

@Controller
@RequestMapping("/secure/post")
public class SecurePostController {
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	TagRepository tagRepository;

	@Autowired
	TagCreatorUtil tagCreatorUtil;

	
	@RequestMapping("/new")
	public String createPost(Model model) {
		model.addAttribute("post", new Post());
		model.addAttribute("postPart", new PostPart());
		return "blog/managePost";
	}

	@RequestMapping("/edit/{postId}")
	public String editPost(@PathVariable Integer postId, Model model) {
		model.addAttribute("post", postRepository.findOne(postId));
		return "blog/managePost";
	}

	@RequestMapping("/save")
	public String savePost(@ModelAttribute Post post,
			@ModelAttribute PostPart postPart, @RequestParam("tags") String tags) {

		post.setAuthor("Kevin Bowersox");
		post.setPostedDt(new Date());
		post.setPostPart(postPart);
		post.setTags(tagCreatorUtil.createTags(tags));

		postRepository.save(post);

		return "redirect:/blog/";
	}

	@RequestMapping("/delete/{postId}")
	public String deletePost(@PathVariable Integer postId, Model model) {
		postRepository.delete(postId);
		return "redirect:/blog/";
	}

}
