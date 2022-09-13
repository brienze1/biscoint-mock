package org.brienze.biscoint.webservice;

import org.brienze.biscoint.adapter.BiscointWebServiceAdapter;
import org.brienze.biscoint.builder.HeaderBuilder;
import org.brienze.biscoint.dto.BiscointDto;
import org.brienze.biscoint.enums.Quote;
import org.brienze.biscoint.model.Bitcoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class BiscointWebService implements BiscointWebServiceAdapter {

    @Value("${biscoint.api.url.get.value}")
    private String url;

    @Autowired
    private HeaderBuilder headerBuilder;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Bitcoin getBitcoinUnitaryValue() {

        HttpHeaders headers = headerBuilder.buildBiscointHeader();

        HttpEntity<?> request = new HttpEntity<>(headers);

        ResponseEntity<BiscointDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                BiscointDto.class);

        return Objects.requireNonNull(response.getBody()).getBitcoinDto().toBitcoin();
    }

}
