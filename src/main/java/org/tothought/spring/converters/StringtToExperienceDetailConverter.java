package org.tothought.spring.converters;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.tothought.entities.ExperienceDetail;
import org.tothought.repositories.ExperienceDetailRepository;

public class StringtToExperienceDetailConverter implements Converter<String, ExperienceDetail> {

	@Autowired
	ExperienceDetailRepository repository;
	
	@Override
	public ExperienceDetail convert(String source) {
		ExperienceDetail detail = new ExperienceDetail();
		
		if(this.isEdit(source)){
			detail = repository.findOne(new Integer(source));
		}else if(this.isInsert(source)){
			detail.setDescription(source);
		}

		return detail;
	}
	
	private boolean isInsert(String text) {
		return !StringUtils.isEmpty(text);
	}

	private boolean isEdit(String text) {
		return NumberUtils.isDigits(text);
	}

}
