package org.tothought.spring.converters;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.tothought.entities.DegreeDetail;
import org.tothought.repositories.DegreeDetailRepository;

public class StringToDegreeDetailConverter implements Converter<String, DegreeDetail> {

	@Autowired
	DegreeDetailRepository repository;

	@Override
	public DegreeDetail convert(String source) {
		DegreeDetail detail = new DegreeDetail();

		if (this.isEdit(source)) {
			detail = repository.findOne(new Integer(source));
		} else if (this.isInsert(source)) {
			detail = new DegreeDetail();
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
