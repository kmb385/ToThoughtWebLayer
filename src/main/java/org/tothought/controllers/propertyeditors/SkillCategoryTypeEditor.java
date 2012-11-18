package org.tothought.controllers.propertyeditors;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.math.NumberUtils;
import org.tothought.entities.SkillCategory;
import org.tothought.repositories.SkillCategoryRepository;


public class SkillCategoryTypeEditor extends PropertyEditorSupport {

	SkillCategoryRepository repository;
	
	public SkillCategoryTypeEditor(SkillCategoryRepository repository){
		this.repository = repository;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(NumberUtils.isDigits(text)){
			SkillCategory skillCategory = repository.findOne(new Integer(text));
			
			if(skillCategory != null){
				setValue(skillCategory);
			}else{
				new SkillCategory();
			}
		}
	}

	@Override
	public String getAsText() {
		SkillCategory skillCategory = (SkillCategory) this.getValue();
		if(skillCategory != null){
			return skillCategory.getSkillCategoryId().toString();			
		}
		return super.getAsText();
	}

	@Override
	public void setValue(Object value) {
		super.setValue(value);
	}
	
	

	
}
