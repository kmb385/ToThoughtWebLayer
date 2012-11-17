package org.tothought.controllers.resume;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
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
			
	@RequestMapping("/")
	public String manage(){
		return "resume/manager/manager";
	}	
}
