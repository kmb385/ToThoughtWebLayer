package org.tothought.spring.controllers.resume;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tothought.entities.Skill;
import org.tothought.entities.interfaces.SkillDetail;
import org.tothought.json.JsonUtil;
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
	 * Display all details when more link is clicked via non-ajax request.
	 * 
	 * @param model
	 * @param skillId
	 * @param detail
	 * @return
	 */
	@RequestMapping("/{skillId}/detail/{detail}")
	public String getSkill(Model model, @PathVariable Integer skillId, @PathVariable String detail) {
		this.handleSkillRequest(model, skillId, detail, null);
		return "resume/skill";
	}
	
	/**
	 * Returns JSON of all details for ajax request.
	 * 
	 * @param model
	 * @param skillId
	 * @param detail
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/{skillId}/detail/{detail}", produces="application/json")
	public String getSkillJson(Model model, @PathVariable Integer skillId, @PathVariable String detail) {
		return this.getDetailJson(skillId, detail, null);
	}
	
	/**
	 * Returns JSON of a page of details for ajax request.
	 * 
	 * @param model
	 * @param skillId
	 * @param detail
	 * @return
	 */
	@RequestMapping(value="/{skillId}/detail/{detail}/detailpage/{detailpage}", produces="application/json")
	@ResponseBody
	public String getSkillJson(@PathVariable Integer skillId, @PathVariable String detail, @PathVariable Integer detailpage) {
		return this.getDetailJson(skillId, detail, detailpage);
	}

	/**
	 * Display a page of details, used for non-ajax requests.
	 * 
	 * @param model
	 * @param skillId
	 * @param detail
	 * @param detailpage
	 * @return
	 */
	@RequestMapping("/{skillId}/detail/{detail}/detailpage/{detailpage}")
	@PageableRequestMapping(pathVariable = "detailpage")
	public String getSkill(Model model, @PathVariable Integer skillId, @PathVariable String detail,
			@PathVariable Integer detailpage) {
		this.handleSkillRequest(model, skillId, detail, detailpage);
		return "resume/skill";
	}

	/**
	 * Return the appropriate list of skills.
	 * @param model
	 * @param skillId
	 * @param detailType
	 * @param page
	 */
	private void handleSkillRequest(Model model, Integer skillId, String detailType, Integer page) {
		List<? extends SkillDetail> details = null;
		Skill skill = skillRepository.findOne(skillId);
		String tagName = (skill.getTag() != null) ? skill.getTag().getName() : null;

		details = findDetails(detailType, page, tagName);

		model.addAttribute("skill", skill);
		model.addAttribute("detailType", detailType);
		model.addAttribute("details", details);
	}

	/**
	 * Returns a list of Details based upon request parameters.
	 * @param detailType
	 * @param page
	 * @param tagName
	 * @return
	 */
	private List<? extends SkillDetail> findDetails(String detailType, Integer page,String tagName) {
		List<? extends SkillDetail> details = null;
		if (page == null) {
			if (detailType.equalsIgnoreCase("github")) {
				details = commitRepository.findByTag(tagName);
			} else if (detailType.equalsIgnoreCase("stack")) {
				details = answerRepository.findByTag(tagName);
			}
		} else {
			if (detailType.equalsIgnoreCase("github")) {
				PageRequest pageRequest = new PageRequest(page, this.pageSize, this.commitSort);
				details = commitRepository.pageByTag(tagName, pageRequest).getContent();
			} else if (detailType.equalsIgnoreCase("stack")) {
				PageRequest pageRequest = new PageRequest(page, this.pageSize, this.answerSort);
				details = answerRepository.pageByTag(tagName, pageRequest).getContent();
			}
		}
		return details;
	}
	
	/**
	 * Returns Json for based upon parameters specified by request.
	 * @param skillId
	 * @param detail
	 * @param detailPage
	 * @return
	 */
	private String getDetailJson(Integer skillId, String detail, Integer detailPage){
		List<? extends SkillDetail> details = null;
		Skill skill = skillRepository.findOne(skillId);
		String tagName = (skill.getTag() != null) ? skill.getTag().getName() : null;
		
		details = this.findDetails(detail, detailPage, tagName);
		return JsonUtil.getJson(details);
	}
}
