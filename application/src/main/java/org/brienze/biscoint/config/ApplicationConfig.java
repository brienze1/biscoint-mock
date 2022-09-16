package org.brienze.biscoint.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.brienze.biscoint.filter.AuthorizationValidatorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Configuration
public class ApplicationConfig {

    private static final String OBJECT_MAPPER_FORMAT_DATE = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String OBJECT_MAPPER_TIME_ZONE = "America/Sao_Paulo";

    @Autowired
    private AuthorizationValidatorFilter authorizationValidatorFilter;

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
        registrationBean.addUrlPatterns("/v1/offer/confirm");
        registrationBean.addUrlPatterns("/v1/balance");

        return registrationBean;
    }

}
