package org.tothought.spring.propertyeditors;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.tothought.entities.ExperienceDetail;
import org.tothought.repositories.ExperienceDetailRepository;


public class ExperienceDetailTypeEditor extends PropertyEditorSupport {

	private ExperienceDetailRepository repository;
	
	public ExperienceDetailTypeEditor(ExperienceDetailRepository repository){
		this.repository = repository;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		ExperienceDetail detail;
		
		if(this.isEdit(text)){
			detail = repository.findOne(new Integer(text));
		}else if(this.isInsert(text)){
			detail = new ExperienceDetail();
			detail.setDescription(text);
		}else{
			detail = new ExperienceDetail(); 
		}
		
		setValue(detail);
	}

	private boolean isInsert(String text) {
		return !StringUtils.isEmpty(text);
	}

	private boolean isEdit(String text) {
		return NumberUtils.isDigits(text);
	}
}
