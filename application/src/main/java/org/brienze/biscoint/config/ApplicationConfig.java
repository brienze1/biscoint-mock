package org.brienze.biscoint.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.brienze.biscoint.filter.AuthorizationValidatorFilter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class ApplicationConfig {

	private static final String OBJECT_MAPPER_FORMAT_DATE = "yyyy-MM-dd'T'HH:mm:ss";
	private static final String OBJECT_MAPPER_TIME_ZONE = "America/Sao_Paulo";

	@Autowired
	private AuthorizationValidatorFilter authorizationValidatorFilter;
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public ObjectMapper objectMapper() {
		final DateFormat df = new SimpleDateFormat(OBJECT_MAPPER_FORMAT_DATE);
		return new ObjectMapper().registerModule(new JavaTimeModule()).setDateFormat(df)
				.setTimeZone(TimeZone.getTimeZone(OBJECT_MAPPER_TIME_ZONE));
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public FilterRegistrationBean<AuthorizationValidatorFilter> loggingFilter() {
		FilterRegistrationBean<AuthorizationValidatorFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(authorizationValidatorFilter);
		registrationBean.addUrlPatterns("/v1/offer");

		return registrationBean;
	}

}
