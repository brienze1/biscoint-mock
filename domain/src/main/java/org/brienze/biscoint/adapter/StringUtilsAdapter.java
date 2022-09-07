package org.brienze.biscoint.adapter;

import com.fasterxml.jackson.databind.JsonNode;

public interface StringUtilsAdapter {

	String parseToString(Object object);

	JsonNode parseToJson(String body);

}
