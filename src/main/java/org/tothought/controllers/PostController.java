package org.tothought.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tothought.entities.Post;
import org.tothought.entities.PostPart;
import org.tothought.entities.PostView;
import org.tothought.entities.Tag;
import org.tothought.json.JsonUtil;
import org.tothought.repositories.PostRepository;
import org.tothought.repositories.PostViewRepository;
import org.tothought.repositories.TagRepository;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	PostRepository postRepository;

	@Autowired
	TagRepository tagRepository;

	@Autowired
	PostViewRepository postViewRepository;

	@RequestMapping("/{postId}")
	public String getPost(@PathVariable Integer postId, Model model) {
		model.addAttribute("post", postViewRepository.findOne(postId));
		return "blog/post";
	}

	@RequestMapping("/{postId}/tags")
	@ResponseBody
	public String getTags(@PathVariable Integer postId, Model model) {
		PostView postView = postViewRepository.findOne(postId);
		return JsonUtil.getJson(postView.getTags());
	}

	@RequestMapping("/new")
	public String createPost(Model model) {
		model.addAttribute("post", new Post());
		model.addAttribute("postPart", new PostPart());
		return "blog/managePost";
	}

	@RequestMapping("/save")
	public String savePost(@ModelAttribute Post post,
			@ModelAttribute PostPart postPart, @RequestParam("tags") String tags) {

		post.setAuthor("Kevin Bowersox");
		post.setPostedDt(new Date());
		post.setPostPart(postPart);
		post.setTags(this.createTags(tags));

		postRepository.save(post);

		return "redirect:/blog/";
	}

	@RequestMapping("/{postId}/edit")
	public String editPost(@PathVariable Integer postId, Model model) {
		model.addAttribute("post", postRepository.findOne(postId));
		return "blog/managePost";
	}

	@RequestMapping("/{postId}/delete")
	public String deletePost(@PathVariable Integer postId, Model model) {
		postRepository.delete(postId);
		return "redirect:/blog/";
	}

	private List<Tag> createTags(String csvTags) {
		List<Tag> tags = new ArrayList<Tag>();
		String[] tagValues = csvTags.split(",");

		for (String tag : tagValues) {
			if (!StringUtils.isEmpty(tag)) {
				if (!NumberUtils.isNumber(tag)) {
					tags.add(new Tag(tag));
				} else {
					tags.add(tagRepository.findOne(new Integer(tag)));
				}
			}
		}
		return tags;
	}

}
