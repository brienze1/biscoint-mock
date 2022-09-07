package org.brienze.biscoint.utils;

import org.brienze.biscoint.adapter.StringUtilsAdapter;
import org.brienze.biscoint.exception.ValidationException;
import org.brienze.biscoint.validator.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class StringUtils implements StringUtilsAdapter {

	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public String parseToString(Object object) {
		Validators.validateNotNull(object, "Could not parse object to string. Object is null.");
		
		try {
			return mapper.writeValueAsString(mapper.writeValueAsString(object));
		} catch (JsonProcessingException e) {
			throw new ValidationException("Could not parse object to string. ".concat(e.getMessage()));
		}
	}

	@Override
	public JsonNode parseToJson(String body) {
		Validators.validateNotEmpty(body, "Could not parse string to json. String is empty.");
		
		try {
			return mapper.readValue(body, JsonNode.class);
		} catch (JsonProcessingException e) {
			throw new ValidationException("Could not parse string to json. ".concat(e.getMessage()));
		}
	}

}
