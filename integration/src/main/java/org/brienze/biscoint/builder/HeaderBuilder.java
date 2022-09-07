package org.brienze.biscoint.builder;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class HeaderBuilder {

	public HttpHeaders buildBiscointHeader() {
		return new HttpHeaders();
	}

}
