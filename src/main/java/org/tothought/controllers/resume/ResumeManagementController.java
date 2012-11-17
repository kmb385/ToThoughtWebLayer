package org.tothought.controllers.resume;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.tothought.controllers.listeners.LookupLoaderApplicationListener;
import org.tothought.controllers.propertyeditors.SkillCategoryTypeEditor;
import org.tothought.entities.Image;
import org.tothought.entities.Skill;
import org.tothought.entities.SkillCategory;
import org.tothought.repositories.SkillCategoryRepository;
import org.tothought.repositories.SkillRepository;

@Controller
@RequestMapping("/resume/manager")
public class ResumeManagementController {
	
	Logger logger = LoggerFactory.getLogger(ResumeManagementController.class);
	
	@Autowired
	LookupLoaderApplicationListener myValues;
	
	@Autowired
	SkillCategoryRepository skillCategoryRepository;
	
	@Autowired
	SkillRepository skillRepository;
	
	@RequestMapping("/")
	public String manage(){
		return "resume/manager/manager";
	}
	
	@RequestMapping("/tech")
	public String manageTech(Model model){
		model.addAttribute("skill", new Skill());
		model.addAttribute("skillCategory", new SkillCategory());
		return "resume/manager/manageTech";
	}
	
	@RequestMapping("/tech/save")
	public String saveTech(@ModelAttribute Skill skill, @RequestParam("file") MultipartFile file) {
		try {
			skill.setImage(this.createImage(file));
			skillRepository.save(skill);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*
		String realPath = request.getServletContext().getRealPath("/");
		File tmpFile = new File(realPath + "/resources/images/resume/tech/uploaded-icons/" + file.getOriginalFilename());
		try {
			FileUtils.writeByteArrayToFile(tmpFile, file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		return "resume/manager/tech";
	}
	
	/**
	 * Sets a binder to handle the conversion of the file.
	 * @param binder
	 */
	@InitBinder
	public void initBinderAll(WebDataBinder binder){
		binder.registerCustomEditor(SkillCategory.class, new SkillCategoryTypeEditor(this.skillCategoryRepository));
	}
	
	public Image createImage(MultipartFile file) throws IOException{
		Image image = new Image();
		image.file = file.getBytes();
		image.name = file.getOriginalFilename();
		image.type = file.getContentType();
		return image;
	}
}
