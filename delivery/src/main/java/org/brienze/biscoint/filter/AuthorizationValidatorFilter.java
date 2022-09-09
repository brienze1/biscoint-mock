package org.brienze.biscoint.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.brienze.biscoint.adapter.StringUtilsAdapter;
import org.brienze.biscoint.useCases.AuthorizeRequestUseCase;
import org.brienze.biscoint.wrapper.RequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

@Component
public class AuthorizationValidatorFilter implements Filter {

	private static final String NONCE = "BSCNT-NONCE";
	private static final String API_KEY = "BSCNT-APIKEY";
	private static final String SIGN = "BSCNT-SIGN";
	
	@Autowired
	private AuthorizeRequestUseCase authorizeRequestUseCase;
	
	@Autowired
	private StringUtilsAdapter stringUtils;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		RequestWrapper wrappedRequest = new RequestWrapper((HttpServletRequest) request);
		
		JsonNode body = stringUtils.parseToJson(wrappedRequest.getBody());
		String path = wrappedRequest.getRequestURI().substring(1);
		String nonce = wrappedRequest.getHeader(NONCE);
		String apiKey = wrappedRequest.getHeader(API_KEY);
		String sign = wrappedRequest.getHeader(SIGN);
		
		authorizeRequestUseCase.authorizeRequest(body, path, nonce, apiKey, sign);
		
		chain.doFilter(wrappedRequest, response);
	}

}
