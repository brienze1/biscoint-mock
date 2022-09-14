package org.brienze.biscoint.cucumber.steps;

import io.cucumber.java.en.Given;
import org.brienze.biscoint.cucumber.context.Context;
import org.brienze.biscoint.dto.ConfirmOfferRequestDto;
import org.brienze.biscoint.dto.OfferResponseDto;
import org.brienze.biscoint.entity.ClientEntity;
import org.brienze.biscoint.entity.OfferEntity;
import org.brienze.biscoint.enums.Quote;
import org.brienze.biscoint.repository.ClientRepository;
import org.brienze.biscoint.repository.OfferRepository;
import org.brienze.biscoint.useCases.SignTokenUseCase;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class ConfirmOfferTestSteps {

    private static final String CONFIRM_OFFER_PATH = "v1/offer/confirm";
    private static final String NONCE = "123456789";

    @LocalServerPort
    private int serverPort;

    @Autowired
    private SignTokenUseCase signTokenUseCase;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Given("is confirmed within time")
    public void isConfirmedWithinTime() {
        OfferResponseDto offerRequestDto = (OfferResponseDto) Context.getInstance().get("response", ResponseEntity.class).getBody();
        Assert.assertNotNull(offerRequestDto);

        confirmOffer(offerRequestDto.getOffer().getOfferId());
    }

    @Given("is not confirmed within time")
    public void isNotConfirmedWithinTime() {
        OfferResponseDto offerRequestDto = (OfferResponseDto) Context.getInstance().get("response", ResponseEntity.class).getBody();
        Assert.assertNotNull(offerRequestDto);

        OfferEntity offerEntity = offerRepository.findById(offerRequestDto.getOffer().getOfferId()).orElseThrow(() -> new RuntimeException("offer is null"));
        Assert.assertNotNull(offerEntity);
        offerEntity.setExpiresAt(LocalDateTime.now().minus(Duration.ofSeconds(30)));
        offerRepository.save(offerEntity);

        confirmOffer(offerRequestDto.getOffer().getOfferId());
    }

    @Given("the client {string} balance should be equal {double}")
    public void theClientBalanceShouldBeEqual(String quote, double balance) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(Context.getInstance().get("api_key", String.class));
        Assert.assertTrue(clientEntity.isPresent());

        if (quote.equalsIgnoreCase(Quote.BTC.name())) {
            Assert.assertEquals(BigDecimal.valueOf(balance).setScale(8, RoundingMode.HALF_UP), clientEntity.get().getBitcoinBalance());
        } else {
            Assert.assertEquals(BigDecimal.valueOf(balance).setScale(2, RoundingMode.HALF_UP), clientEntity.get().getBrlBalance());
        }
    }

    @Given("it's id is saved as {string}")
    public void itSIdIsSavedAs(String idTag) {
        OfferResponseDto offerRequestDto = (OfferResponseDto) Context.getInstance().get("response", ResponseEntity.class).getBody();
        Assert.assertNotNull(offerRequestDto);

        Context.getInstance().set(idTag, offerRequestDto.getOffer().getOfferId());
    }

    @Given("{string} is confirmed within time")
    public void isConfirmedWithinTime(String idTag) {
        confirmOffer(Context.getInstance().get(idTag, String.class));
    }

    public void confirmOffer(String offerId) {
        ConfirmOfferRequestDto confirmOfferRequestDto = new ConfirmOfferRequestDto();
        confirmOfferRequestDto.setOfferId(offerId);

        Context.getInstance().set("token", signTokenUseCase.signToken(confirmOfferRequestDto,
                CONFIRM_OFFER_PATH,
                NONCE,
                Context.getInstance().get("api_secret", String.class)));

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("BSCNT-NONCE", NONCE);
            headers.add("BSCNT-APIKEY", Context.getInstance().get("api_key", String.class));
            headers.add("BSCNT-SIGN", Context.getInstance().get("token", String.class));
            headers.add("Content-Type", "application/json");

            HttpEntity<?> httpEntity = new HttpEntity<>(confirmOfferRequestDto, headers);

            ResponseEntity<OfferResponseDto> response = restTemplate.exchange(
                    "http://localhost:" + this.serverPort + "/" + CONFIRM_OFFER_PATH,
                    HttpMethod.POST,
                    httpEntity,
                    OfferResponseDto.class);

            Context.getInstance().set("response_status", response.getStatusCodeValue());
            Context.getInstance().set("response", response);
        } catch (HttpClientErrorException ex) {
            Context.getInstance().set("response_exception", ex);
            Context.getInstance().set("response_status", ex.getRawStatusCode());
        }
    }

}
