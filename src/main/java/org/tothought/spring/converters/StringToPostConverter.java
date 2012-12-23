package org.tothought.spring.converters;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.tothought.entities.Post;
import org.tothought.repositories.PostRepository;

public class StringToPostConverter implements Converter<String, Post> {

	@Autowired
	PostRepository repository;
	
	@Override
	public Post convert(String source) {
		Post post = null;
		if(!StringUtils.isEmpty(source)){
			post = repository.findOne(new Integer(source));
		}
		return post;
	}

}
