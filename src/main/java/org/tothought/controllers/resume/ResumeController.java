package org.tothought.controllers.resume;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
/*
	@RequestMapping("/tech/manage")
	public String manageTech() {
		return "resume/manageTech";
	}

	@RequestMapping("/tech/save")
	public String saveTech(@RequestParam("title") String name, @RequestParam("rating") int rating,
			@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		logger.info(file.getContentType());
		String realPath = request.getServletContext().getRealPath("/");
		File tmpFile = new File(realPath + "/resources/images/resume/tech/uploaded-icons/" + file.getOriginalFilename());
		try {
			FileUtils.writeByteArrayToFile(tmpFile, file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "resume/manageTech";
	}
	*/
}
