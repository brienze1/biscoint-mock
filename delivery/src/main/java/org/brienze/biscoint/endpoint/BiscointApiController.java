package org.brienze.biscoint.endpoint;

import org.brienze.biscoint.dto.ConfirmOfferRequestDto;
import org.brienze.biscoint.dto.OfferRequestDto;
import org.brienze.biscoint.dto.OfferResponseDto;
import org.brienze.biscoint.model.Offer;
import org.brienze.biscoint.model.OfferRequest;
import org.brienze.biscoint.useCases.ConfirmOfferUseCase;
import org.brienze.biscoint.useCases.CreateOfferUseCase;
import org.brienze.biscoint.useCases.SignTokenUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BiscointApiController {

    private static final String NONCE = "BSCNT-NONCE";
    private static final String API_KEY = "BSCNT-APIKEY";
    private static final String PATH = "BSCNT-PATH";

    @Autowired
    private CreateOfferUseCase createOfferUseCase;

    @Autowired
    private ConfirmOfferUseCase confirmOfferUseCase;

    @Autowired
    private SignTokenUseCase signTokenUseCase;

    @PostMapping("/v1/offer")
    public OfferResponseDto createOffer(@RequestBody OfferRequestDto offerRequestDto, @RequestHeader HttpHeaders headers) {
        OfferRequest offerRequest = offerRequestDto.toOffer();

        Offer offer = createOfferUseCase.createOffer(offerRequest, headers.getFirst(API_KEY));

        return new OfferResponseDto(offer);
    }

    @PostMapping("/v1/offer/confirm")
    public OfferResponseDto confirmOffer(@RequestBody ConfirmOfferRequestDto confirmOfferRequestDto, @RequestHeader HttpHeaders headers) {
        Offer offer = confirmOfferUseCase.confirmOffer(confirmOfferRequestDto.getOfferId(), headers.getFirst(API_KEY));

        return new OfferResponseDto(offer);
    }

    @PostMapping("/v1/auth")
    public ResponseEntity<Map<String, String>> generateToken(@RequestBody Object request, @RequestHeader HttpHeaders headers) {
        String nonce = headers.getFirst(NONCE);
        String apiKey = headers.getFirst(API_KEY);
        String path = headers.getFirst(PATH);

        Map<String, String> response = new HashMap<>();
        response.put("token", signTokenUseCase.signToken(request, path, nonce, apiKey));

        return ResponseEntity.ok().body(response);
    }

}
