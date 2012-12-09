package org.tothought.spring.controllers.resume;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tothought.entities.Experience;
import org.tothought.json.JsonUtil;
import org.tothought.repositories.DegreeRepository;
import org.tothought.repositories.ExperienceRepository;
import org.tothought.repositories.SkillCategoryRepository;
import org.tothought.repositories.SkillRepository;
@Controller
@RequestMapping("/resume")
public class ResumeController {

	Logger logger = LoggerFactory.getLogger(ResumeController.class);

	@Autowired
	SkillCategoryRepository skillCategoryRepository;

	@Autowired
	SkillRepository skillRepository;
	
	@Autowired
	ExperienceRepository experienceRepository;
	
	@Autowired
	DegreeRepository degreeRepository;
	
	@RequestMapping("/profile")
	public String profile() {
		return "resume/profile";
	}

	@RequestMapping("/skills")
	public String getAllSkills(Model model) {
		model.addAttribute("categories", skillCategoryRepository.findAll());
		return "resume/skills";
	}
	
	@RequestMapping("/skills/{skillId}")
	public String getSkill(Model model, @PathVariable Integer skillId ) {
		model.addAttribute("skill", skillRepository.findOne(skillId));
		return "resume/skill";
	}
		
	@RequestMapping("/experience")
	public String getExperience(Model model){
		model.addAttribute("experiences", experienceRepository.findAll());
		return  "resume/experience";
	}
	
	@RequestMapping("/experience/{experienceId}/tags")
	@ResponseBody
	public String getTags(@PathVariable Integer experienceId) {
		Experience experience = experienceRepository.findOne(experienceId);
		return JsonUtil.getJson(experience.getTags());
	}
	
	@RequestMapping("/degree")
	public String getDegrees(Model model){
		model.addAttribute("degrees", degreeRepository.findAll());
		return "resume/degrees";
	}
}
