package org.tothought.spring.controllers.resume;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/secure/resume/manager")
public class SecureResumeManagerController {
	
	@Secured("ROLE_ADMIN")
	@RequestMapping("/")
	public String manage(){
		return "resume/manager/manager";
	}	
}
