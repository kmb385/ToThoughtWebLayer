package org.tothought.spring.controllers.blog;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tothought.email.CommentMessage;
import org.tothought.email.EmailService;
import org.tothought.entities.Comment;
import org.tothought.repositories.CommentRepository;
import org.tothought.repositories.PostViewRepository;
import org.tothought.repositories.TagViewRepository;
import org.tothought.spring.utilities.RecaptchaService;
import org.tothought.validators.CommentValidator;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {

	@Autowired
	CommentRepository repository;

	@Autowired
	PostViewRepository postViewRepository;

	@Autowired
	TagViewRepository tagViewRepository;

	@Autowired
	EmailService emailService;
	
	@Autowired
	RecaptchaService recaptchaService;

	@RequestMapping(value = "/save")
	public String save(@Valid @ModelAttribute Comment comment, BindingResult result, Model model,
			HttpServletRequest request) {

		final Integer postId = comment.getPost().getPostId();
		
		if (result.hasErrors() || !recaptchaService.isValid(request)) {

			model.addAttribute("post", postViewRepository.findOne(postId));
			model.addAttribute("isSingle", true);
			model.addAttribute("tags", tagViewRepository.findAll(new Sort(Direction.ASC, "name")));
			model.addAttribute("captcha", recaptchaService.getRecaptcha());
			model.addAttribute("captchaError", recaptchaService.getErrorMsg());
			return "blog/post";

		} else {

				comment.setPostedDt(new Date());
				repository.save(comment);
				this.sendEmail(comment);

				return "redirect:/post/" + postId.toString();
		}
	}

	private void sendEmail(Comment comment) {
		// Send notification email
		CommentMessage commentMessage = new CommentMessage();
		commentMessage.setBody(comment);
		emailService.sendMessage(commentMessage);
	}

	@InitBinder("comment")
	public void initBinderAll(WebDataBinder binder) {
		binder.setValidator(new CommentValidator());
	}
}
