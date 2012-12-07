package org.tothought.spring.propertyeditors;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.tothought.entities.DegreeDetail;
import org.tothought.repositories.DegreeDetailRepository;


public class DegreeDetailTypeEditor extends PropertyEditorSupport {

	private DegreeDetailRepository repository;
	
	public DegreeDetailTypeEditor(DegreeDetailRepository repository){
		this.repository = repository;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		DegreeDetail detail;
		
		if(this.isEdit(text)){
			detail = repository.findOne(new Integer(text));
		}else if(this.isInsert(text)){
			detail = new DegreeDetail();
			detail.setDescription(text);
		}else{
			detail = new DegreeDetail(); 
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
