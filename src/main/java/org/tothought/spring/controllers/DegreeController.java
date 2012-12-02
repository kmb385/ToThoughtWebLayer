package org.tothought.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resume/manager/degree")
public class DegreeController {

	@RequestMapping("/")
	public String getDegrees(){
		return "degrees";
	}
}
