package org.brienze.biscoint.endpoint;

import org.brienze.biscoint.dto.*;
import org.brienze.biscoint.model.Balance;
import org.brienze.biscoint.model.Bitcoin;
import org.brienze.biscoint.model.Offer;
import org.brienze.biscoint.model.OfferRequest;
import org.brienze.biscoint.useCases.BitcoinUseCase;
import org.brienze.biscoint.useCases.ClientUseCase;
import org.brienze.biscoint.useCases.OfferUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
public class BiscointApiController {

    private static final String NONCE = "BSCNT-NONCE";
    private static final String API_KEY = "BSCNT-APIKEY";
    private static final String PATH = "BSCNT-PATH";

    @Autowired
    private OfferUseCase offerUseCase;

    @Autowired
    private ClientUseCase clientUseCase;

    @Autowired
    private BitcoinUseCase bitcoinUseCase;

    @PostMapping("/v1/offer")
    public OfferResponseDto createOffer(@RequestBody OfferRequestDto offerRequestDto, @RequestHeader HttpHeaders headers) {
        OfferRequest offerRequest = offerRequestDto.toOffer();

        Offer offer = offerUseCase.createOffer(offerRequest, headers.getFirst(API_KEY));

        return new OfferResponseDto(offer);
    }

    @PostMapping("/v1/offer/confirm")
    public OfferResponseDto confirmOffer(@RequestBody ConfirmOfferRequestDto confirmOfferRequestDto, @RequestHeader HttpHeaders headers) {
        Offer offer = offerUseCase.confirmOffer(confirmOfferRequestDto.getOfferId(), headers.getFirst(API_KEY));

        return new OfferResponseDto(offer);
    }

    @PostMapping("/v1/balance")
    public BalanceResponseDto getBalance(@RequestHeader HttpHeaders headers) {
        Balance balance = clientUseCase.getBalance(headers.getFirst(API_KEY));

        return new BalanceResponseDto(balance);
    }

    @GetMapping("/v1/ticker")
    public TickerResponseDto getTicker(@RequestHeader HttpHeaders headers) {
        Bitcoin bitcoin = bitcoinUseCase.getUnitaryValue();

        return new TickerResponseDto(bitcoin);
    }

    @PostMapping("/v1/auth")
    public TokenResponseDto generateToken(@RequestBody Object request, @RequestHeader HttpHeaders headers) {
        String nonce = headers.getFirst(NONCE);
        String apiKey = headers.getFirst(API_KEY);
        String path = headers.getFirst(PATH);

        return new TokenResponseDto(clientUseCase.signToken(request, path, nonce, apiKey));
    }

}
