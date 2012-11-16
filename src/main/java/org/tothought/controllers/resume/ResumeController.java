package org.tothought.controllers.resume;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;
@Controller
@RequestMapping("/resume")
public class ResumeController {

	Logger logger = LoggerFactory.getLogger(ResumeController.class);

	@RequestMapping("/profile")
	public String profile() {
		return "resume/profile";
	}

	@RequestMapping("/tech")
	public String tech() {
		return "resume/tech";
	}

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
}
