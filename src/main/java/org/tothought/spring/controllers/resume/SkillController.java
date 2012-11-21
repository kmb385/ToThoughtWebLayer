package org.tothought.spring.controllers.resume;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.tothought.entities.Image;
import org.tothought.entities.Skill;
import org.tothought.entities.SkillCategory;
import org.tothought.entities.Tag;
import org.tothought.json.JsonUtil;
import org.tothought.repositories.SkillCategoryRepository;
import org.tothought.repositories.SkillRepository;
import org.tothought.spring.propertyeditors.SkillCategoryTypeEditor;
import org.tothought.spring.utilities.ImageCreatorUtil;
import org.tothought.spring.utilities.TagCreatorUtil;

@Controller
@RequestMapping("/resume/manager/skills")
public class SkillController {

	Logger logger = LoggerFactory.getLogger(ResumeManagementController.class);

	@Autowired
	SkillCategoryRepository skillCategoryRepository;

	@Autowired
	SkillRepository skillRepository;

	@Autowired
	ImageCreatorUtil imageCreatorUtil;
	
	@Autowired
	TagCreatorUtil tagCreatorUtil;
	
	@RequestMapping("/")
	public String manageSkills(Model model) {
		model.addAttribute("skill", new Skill());
		model.addAttribute("skillCategory", new SkillCategory());
		return "resume/manager/manageSkill";
	}

	@RequestMapping("/new")
	public String newSkill(Model model) {
		model.addAttribute("skill", new Skill());
		model.addAttribute("skillCategory", new SkillCategory());
		return "resume/manager/manageSkill";
	}

	@RequestMapping("/{skillId}/edit")
	public String editSkill(Model model, @PathVariable Integer skillId) {
		Skill skill = skillRepository.findOne(skillId);
		model.addAttribute("skill", skill);
		return "resume/manager/manageSkill";
	}

	@RequestMapping("/{skillId}/deleteimage")
	public String deleteImage(Model model, @PathVariable Integer skillId) {
		Skill skill = skillRepository.findOne(skillId);
		skill.setImage(null);
		skillRepository.save(skill);
		model.addAttribute("skill", skill);
		return "resume/manager/manageSkill";
	}

	@RequestMapping("/save")
	public String saveSkill(@ModelAttribute Skill skill,
			@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam("tag") String tag) {

		if (file != null) {
			Image image = new Image(file);
			skill.setImage(image);
			
			//This might be best done in a new thread, also may want to move this to abstract if many classes store images.
			imageCreatorUtil.storeImage(image);
		} else if (skill.getSkillId() != null) {
			skill.setImage(skillRepository.findOne(skill.getSkillId()).getImage());
		}
		
		skill.setTag(tagCreatorUtil.createTag(tag));
		skillRepository.save(skill);

		return "redirect:/resume/skills";
	}

	@RequestMapping("/{skillId}/tags")
	@ResponseBody
	public String getTags(@PathVariable Integer skillId) {
		Skill skill = skillRepository.findOne(skillId);
		Tag tag = skill.getTag();
		return JsonUtil.getJson(tag);
	}

	/**
	 * Sets a binder to handle the conversion of the file.
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinderAll(WebDataBinder binder) {
		binder.registerCustomEditor(SkillCategory.class, new SkillCategoryTypeEditor(
				this.skillCategoryRepository));
	}

}
