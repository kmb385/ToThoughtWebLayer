package org.tothought.spring.controllers.blog;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.tothought.entities.Commit;
import org.tothought.json.JsonUtil;
import org.tothought.repositories.CommitRepository;
import org.tothought.spring.utilities.TagCreatorUtil;
import org.tothought.validators.CommitValidator;

@Controller
@RequestMapping("/secure/resume/manager/commit")
public class SecureCommitController {

	@Autowired
	CommitRepository commitRepository;
	
	@Autowired
	TagCreatorUtil tagCreatorUtil;

	/* Model gets loaded prior to the processing of any requests this prevents properties not specified from being
	 * overwritten by databinding, since model attribute is loaded prior to databinding */
	@ModelAttribute("commit")
	public Commit getCommit(@PathVariable Integer commitId){
		return commitRepository.findOne(commitId);
	}
		
	@Secured("ROLE_ADMIN")
	@RequestMapping("/edit/{commitId}")
	public String editCommit(@PathVariable Integer commitId, Model model) {
		model.addAttribute("commit", commitRepository.findOne(commitId));
		return "resume/manager/manageCommit";
	}
	
	/* Needed to add path variable so model could be processed */
	@Secured("ROLE_ADMIN")
	@RequestMapping("/{commitId}/save")
	public String save(@ModelAttribute("commit") @Valid Commit commit, BindingResult result, @RequestParam("tags") String tags){

		if(result.hasErrors()){
			return "resume/manager/manageCommit";
		}
		
		commit.setTags(tagCreatorUtil.createTags(tags));
		commitRepository.save(commit);
		return "redirect:/resume/skills";
	}
	
	@RequestMapping("/{commitId}/tags")
	@ResponseBody
	public String getCommitTags(@PathVariable Integer commitId) {
		Commit commit = commitRepository.findOne(commitId);
		return JsonUtil.getJson(commit.getTags());
	}

	/**
	 * Sets a binder to handle the conversion of the file.
	 * 
	 * @param binder
	 */
	@InitBinder("commit")
	public void initBinderCommitBinder(WebDataBinder binder) {
		binder.setValidator(new CommitValidator());
	}
}
