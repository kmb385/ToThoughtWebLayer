package org.tothought.spring.converters;

import org.springframework.core.convert.converter.Converter;
import org.tothought.entities.SkillCategory;

public class SkillCategoryToStringConverter implements Converter<SkillCategory, String> {

	@Override
	public String convert(SkillCategory source) {
		return source.getSkillCategoryId().toString();
	}

}
