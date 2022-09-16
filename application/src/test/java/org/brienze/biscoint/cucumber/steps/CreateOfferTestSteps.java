package org.brienze.biscoint.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.brienze.biscoint.cucumber.context.Context;
import org.brienze.biscoint.cucumber.mock.BiscointMock;
import org.brienze.biscoint.dto.OfferRequestDto;
import org.brienze.biscoint.dto.OfferResponseDto;
import org.brienze.biscoint.entity.OfferEntity;
import org.brienze.biscoint.enums.Operation;
import org.brienze.biscoint.enums.Quote;
import org.brienze.biscoint.repository.OfferRepository;
import org.brienze.biscoint.useCases.ClientUseCase;
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
import java.util.Optional;
import java.util.stream.StreamSupport;

@SpringBootTest
public class CreateOfferTestSteps {

    private static final String CREATE_OFFER_PATH = "v1/offer";
    private static final String NONCE = "123456789";

    @LocalServerPort
    private int serverPort;

    @Autowired
    private ClientUseCase signTokenUseCase;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private BiscointMock biscointMock;

    @Given("the current bitcoin unitary {string} value is {double}")
    public void theCurrentBitcoinUnitaryValueIs(String operation, double btcUnitaryValue) {
        if (operation.equalsIgnoreCase(Operation.BUY.name())) {
            biscointMock.setBtcUnitaryBuyValue(BigDecimal.valueOf(btcUnitaryValue));
        } else {
            biscointMock.setBtcUnitarySellValue(BigDecimal.valueOf(btcUnitaryValue));
        }
    }

    @Given("the offer db is empty")
    public void theOfferDbIsEmpty() {
        offerRepository.deleteAll();
    }

    @When("an offer is created to {string} {double} {string}")
    public void anOfferIsCreatedTo(String operation, double value, String quote) {
        Context.getInstance().set("quote", quote);

        OfferRequestDto offerRequestDto = new OfferRequestDto();
        offerRequestDto.setAmount(BigDecimal.valueOf(value));
        offerRequestDto.setOperation(operation);
        offerRequestDto.setQuotedOnBrl(quote.equalsIgnoreCase(Quote.BRL.name()));

        Context.getInstance().set("token", signTokenUseCase.signToken(
                offerRequestDto,
                CREATE_OFFER_PATH,
                NONCE,
                Context.getInstance().get("api_secret", String.class)));

        createOffer(offerRequestDto);
    }

    @When("an offer is created to {string} {double} {string} without token")
    public void anOfferIsCreatedToWithoutToken(String operation, double value, String quote) {
        Context.getInstance().set("quote", quote);

        OfferRequestDto offerRequestDto = new OfferRequestDto();
        offerRequestDto.setAmount(BigDecimal.valueOf(value));
        offerRequestDto.setOperation(operation);
        offerRequestDto.setQuotedOnBrl(quote.equalsIgnoreCase(Quote.BRL.name()));

        Context.getInstance().set("token", "");

        createOffer(offerRequestDto);
    }

    @Then("there should be an offer with the same id in the db")
    public void thereShouldBeAnOfferWithTheSameIdInTheDb() {
        OfferResponseDto response = (OfferResponseDto) Context.getInstance().get("response", ResponseEntity.class).getBody();
        Assert.assertNotNull(response);

        Optional<OfferEntity> offerEntity = offerRepository.findById(response.getOffer().getOfferId());

        Assert.assertTrue(offerEntity.isPresent());
        Assert.assertEquals(response.getOffer().getOfferId(), offerEntity.get().toOffer().getOfferId());
        Assert.assertEquals(response.getOffer().getBase(), offerEntity.get().toOffer().getBase().name());
        Assert.assertEquals(response.getOffer().getQuote(), offerEntity.get().toOffer().getQuote().name());
        Assert.assertEquals(response.getOffer().getOperation(), offerEntity.get().toOffer().getOperation().getName());
        Assert.assertEquals(response.getOffer().getQuotedInBrl(), offerEntity.get().toOffer().getQuotedInBrl());
        Assert.assertEquals(response.getOffer().getUnitaryValue(), offerEntity.get().toOffer().getUnitaryValue());
        Assert.assertEquals(response.getOffer().getCreatedAt(), offerEntity.get().toOffer().getCreatedAt());
        Assert.assertEquals(response.getOffer().getExpiresAt(), offerEntity.get().toOffer().getExpiresAt());
        Assert.assertEquals(response.getOffer().getApiKeyId(), offerEntity.get().toOffer().getApiKeyId());

        if (Context.getInstance().get("quote", String.class).equalsIgnoreCase(Quote.BRL.name())) {
            Assert.assertEquals(response.getOffer().getBaseAmount(), offerEntity.get().toOffer().getBaseAmount().setScale(2, RoundingMode.HALF_UP));
            Assert.assertEquals(response.getOffer().getQuoteAmount(), offerEntity.get().toOffer().getQuoteAmount().setScale(8, RoundingMode.HALF_UP));
        } else {
            Assert.assertEquals(response.getOffer().getBaseAmount(), offerEntity.get().toOffer().getBaseAmount().setScale(8, RoundingMode.HALF_UP));
            Assert.assertEquals(response.getOffer().getQuoteAmount(), offerEntity.get().toOffer().getQuoteAmount().setScale(2, RoundingMode.HALF_UP));
        }
    }

    @Then("there should not be an offer in the db")
    public void thereShouldNotBeAnOfferInTheDb() {
        Iterable<OfferEntity> offers = offerRepository.findAll();

        Assert.assertEquals(0, StreamSupport.stream(offers.spliterator(), false).count());
    }


    private void createOffer(OfferRequestDto offerRequestDto) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("BSCNT-NONCE", NONCE);
            headers.add("BSCNT-APIKEY", Context.getInstance().get("api_key", String.class));
            headers.add("BSCNT-SIGN", Context.getInstance().get("token", String.class));
            headers.add("Content-Type", "application/json");

            HttpEntity<?> httpEntity = new HttpEntity<>(offerRequestDto, headers);

            ResponseEntity<OfferResponseDto> response = restTemplate.exchange(
                    "http://localhost:" + this.serverPort + "/" + CREATE_OFFER_PATH,
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
