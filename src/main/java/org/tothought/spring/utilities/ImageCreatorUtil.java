package org.tothought.spring.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.tothought.entities.Image;

@Component
public class ImageCreatorUtil{

	static Logger logger = LoggerFactory.getLogger(ImageCreatorUtil.class);
	static final String IMAGE_PATH_FRAGMENT = "/resources/images/resume/skills/uploaded-icons/";

	@Autowired
	private WebApplicationContext context;
	
	/**
	 * Creates the provided file in the resources directory for access by the
	 * web application.
	 * 
	 * @param appContext
	 * @param image
	 */
	public void storeImage(Image image) {
		if (image != null) {
			String realPath = ((WebApplicationContext)context).getServletContext().getRealPath("/");
			File tmpFile = new File(realPath + IMAGE_PATH_FRAGMENT + image.getName());

			try {
				logger.info("Saving image to :" + tmpFile.getAbsolutePath());
				FileUtils.writeByteArrayToFile(tmpFile, image.getFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
