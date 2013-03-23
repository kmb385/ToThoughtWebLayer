package org.tothought.spring.controllers.resume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tothought.entities.Skill;
import org.tothought.repositories.CommitRepository;
import org.tothought.repositories.SkillCategoryRepository;
import org.tothought.repositories.SkillRepository;
import org.tothought.repositories.StackOverflowAnswerRepository;
import org.tothought.spring.annotations.PageableRequestMapping;

@Controller
@RequestMapping("/resume/skills")
public class SkillController {
	
	private Sort commitSort = new Sort(Direction.DESC, "commitDt");	
	private Sort answerSort = new Sort(Direction.DESC, "createdDt");	
	private int pageSize = 5;

	@Autowired
	SkillRepository skillRepository;

	@Autowired
	SkillCategoryRepository skillCategoryRepository;
	
	@Autowired
	CommitRepository commitRepository;

	@Autowired
	StackOverflowAnswerRepository answerRepository;
	
	@RequestMapping
	public String getAllSkills(Model model) {
		model.addAttribute("categories", skillCategoryRepository.findAll());
		return "resume/skills";
	}

	/**
	 * Display all details.
	 * @param model
	 * @param skillId
	 * @param detail
	 * @return
	 */
	@RequestMapping("/{skillId}/detail/{detail}")
	public String getSkill(Model model, @PathVariable Integer skillId, @PathVariable String detail) {
		Skill skill = skillRepository.findOne(skillId);
		model.addAttribute("skill", skill);

		if(detail.equalsIgnoreCase("github")){				
			model.addAttribute("details", commitRepository.findByTag(skill.getTag().getName()));
		}else if(detail.equalsIgnoreCase("stack")){
			model.addAttribute("details", answerRepository.findByTag(skill.getTag().getName()));
		}
		model.addAttribute("detailType", detail);
		model.addAttribute("commits", commitRepository.findByTag(skill.getTag().getName()));
		return "resume/skill";
	}

	/**
	 * Display a page of details.
	 * @param model
	 * @param skillId
	 * @param detail
	 * @param detailpage
	 * @return
	 */
	@RequestMapping("/{skillId}/detail/{detail}/detailpage/{detailpage}")
	@PageableRequestMapping(pathVariable="detailpage")
	public String getSkill(Model model, @PathVariable Integer skillId, @PathVariable String detail, @PathVariable Integer detailpage){
		Skill skill = skillRepository.findOne(skillId);
		model.addAttribute("skill", skill);
		model.addAttribute("detailType", detail);
		
		if(detail.equalsIgnoreCase("stack")){
			PageRequest pageRequest = new PageRequest(detailpage, this.pageSize, this.answerSort);
			model.addAttribute("details", answerRepository.pageByTag(skill.getTag().getName(),pageRequest).getContent());
		}else if (detail.equalsIgnoreCase("github")){
			PageRequest pageRequest = new PageRequest(detailpage, this.pageSize, this.commitSort);
			model.addAttribute("details", commitRepository.pageByTag(skill.getTag().getName(),pageRequest).getContent());
		}
		return "resume/skill";
	}
	
	/**
	 * Display the Github commits on initial visit.
	 * @param model
	 * @param skillId
	 * @param detailpage
	 * @return
	 */
	@RequestMapping("/{skillId}/detailpage/{detailpage}")
	@PageableRequestMapping(pathVariable="detailpage")
	public String getSkill(Model model, @PathVariable Integer skillId, @PathVariable Integer detailpage) {
		Skill skill = skillRepository.findOne(skillId);
		PageRequest pageRequest = new PageRequest(detailpage, this.pageSize, this.commitSort);

		model.addAttribute("skill", skill);
		model.addAttribute("details", commitRepository.pageByTag(skill.getTag().getName(), pageRequest).getContent());
		model.addAttribute("detailType", "github");
		return "resume/skill";
	}

}
