package org.tothought.spring.controllers.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tothought.entities.Comment;
import org.tothought.repositories.CommentRepository;
import org.tothought.repositories.PostViewRepository;
import org.tothought.repositories.TagViewRepository;

@Controller
@RequestMapping(value="/secure/comment/")
public class SecureCommentController {

	@Autowired
	CommentRepository repository;
	
	@Autowired
	PostViewRepository postViewRepository;
	
	@Autowired
	TagViewRepository tagViewRepository;
	
	@RequestMapping("/{commentId}/delete")
	public String delete(@PathVariable Integer commentId, Model model){
		Comment comment = repository.findOne(commentId);
		model.addAttribute("post", postViewRepository.findOne(comment.getPost().getPostId()));
		
		repository.delete(comment);
		
		model.addAttribute("isSingle", true);
		model.addAttribute("tags", tagViewRepository.findAll(new Sort(Direction.ASC, "name")));
		return "blog/post";		
	}
}
