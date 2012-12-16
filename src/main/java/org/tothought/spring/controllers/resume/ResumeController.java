package org.tothought.spring.controllers.resume;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.tothought.entities.Experience;
import org.tothought.json.JsonUtil;
import org.tothought.pdf.PDF;
import org.tothought.repositories.DegreeRepository;
import org.tothought.repositories.ExperienceRepository;
import org.tothought.repositories.SkillCategoryRepository;
import org.tothought.repositories.SkillRepository;

@Controller
@RequestMapping("/resume")
public class ResumeController {

	Logger logger = LoggerFactory.getLogger(ResumeController.class);
	
	@Autowired
	private WebApplicationContext context;

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
	public String getSkill(Model model, @PathVariable Integer skillId) {
		model.addAttribute("skill", skillRepository.findOne(skillId));
		return "resume/skill";
	}

	@RequestMapping("/experience")
	public String getExperience(Model model) {
		model.addAttribute("experiences", experienceRepository.findAll());
		return "resume/experience";
	}

	@RequestMapping("/experience/{experienceId}/tags")
	@ResponseBody
	public String getTags(@PathVariable Integer experienceId) {
		Experience experience = experienceRepository.findOne(experienceId);
		return JsonUtil.getJson(experience.getTags());
	}

	@RequestMapping("/degree")
	public String getDegrees(Model model) {
		model.addAttribute("degrees", degreeRepository.findAll());
		return "resume/degrees";
	}
	
	@RequestMapping("/full")
	public String getFullResume(Model model){
		model.addAttribute("categories", skillCategoryRepository.findAll());
		model.addAttribute("degrees", degreeRepository.findAll());
		model.addAttribute("experiences", experienceRepository.findAll());
		return "resume/full_resume";
	}

	@RequestMapping(value = "/full/generatePDF")
	public void generatePdf(HttpServletRequest req, HttpServletResponse res) {
		String applicationUrl =  req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort(); 
		String requestUrl = req.getHeader("referer");
		requestUrl = req.getRequestURL().substring(0, req.getRequestURL().lastIndexOf("/"));

		res.setContentType("text/html;charset=UTF-8");
		res.setContentType("application/pdf");
		res.setHeader("Content-Disposition", "attachment;filename=report.pdf");

		try {
			String resourceDirectory = context.getServletContext().getRealPath("/") + "/resources/";
			new PDF(requestUrl, res.getOutputStream(), resourceDirectory, applicationUrl).generate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
