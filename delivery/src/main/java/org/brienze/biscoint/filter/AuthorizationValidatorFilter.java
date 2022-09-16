package org.brienze.biscoint.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.brienze.biscoint.adapter.StringUtilsAdapter;
import org.brienze.biscoint.exception.BiscointApiException;
import org.brienze.biscoint.handler.ErrorHandler;
import org.brienze.biscoint.useCases.ClientUseCase;
import org.brienze.biscoint.wrapper.RequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class AuthorizationValidatorFilter implements Filter {

    private static final String NONCE = "BSCNT-NONCE";
    private static final String API_KEY = "BSCNT-APIKEY";
    private static final String SIGN = "BSCNT-SIGN";
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ClientUseCase clientUseCase;

    @Autowired
    private StringUtilsAdapter stringUtils;

    @Autowired
    private ErrorHandler errorHandler;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        RequestWrapper wrappedRequest = new RequestWrapper((HttpServletRequest) request);

        JsonNode body = stringUtils.parseToJson(wrappedRequest.getBody());
        String path = wrappedRequest.getRequestURI().substring(1);
        String nonce = wrappedRequest.getHeader(NONCE);
        String apiKey = wrappedRequest.getHeader(API_KEY);
        String sign = wrappedRequest.getHeader(SIGN);

        try {
            clientUseCase.authorizeRequest(body, path, nonce, apiKey, sign);
            chain.doFilter(wrappedRequest, response);
        } catch (BiscointApiException ex) {
            Map<String, Object> responseBody = errorHandler.handleError(ex, path);
            ((HttpServletResponse) response).setStatus(ex.getStatusResponse().value());
            ((HttpServletResponse) response).setHeader("Content-Type", "application/json");
            response.getWriter().write(mapper.writeValueAsString(responseBody));
        }
    }

}
