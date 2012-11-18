package org.tothought.controllers.listeners;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.WebApplicationContext;
import org.tothought.entities.Image;
import org.tothought.repositories.ImageRepository;

public class ImageLoaderApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

	Logger logger = LoggerFactory.getLogger(ImageLoaderApplicationListener.class);
	
	@Autowired
	ImageRepository repository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		WebApplicationContext context = (WebApplicationContext) event.getApplicationContext();
		String contextPath = context.getServletContext().getRealPath("/");
		String imagePath = contextPath + "/resources/images/resume/skills/uploaded-icons/";
		
		List<Image> images = repository.findAll();
		
		for(Image image:images){
			try {
				if(!StringUtils.isEmpty(image.getName())){
					logger.info("Image Name: " + image.getName());
					File tmpFile = new File(imagePath + image.getName());
					logger.info("Image Path: " + tmpFile.getAbsolutePath());
					FileUtils.writeByteArrayToFile(tmpFile, image.file);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
