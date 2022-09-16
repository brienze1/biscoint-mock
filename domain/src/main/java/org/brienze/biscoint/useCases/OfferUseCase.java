package org.brienze.biscoint.useCases;

import java.math.BigDecimal;

import org.brienze.biscoint.adapter.ClientRepositoryAdapter;
import org.brienze.biscoint.adapter.OfferRepositoryAdapter;
import org.brienze.biscoint.model.Client;
import org.brienze.biscoint.model.Offer;
import org.brienze.biscoint.model.OfferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfferUseCase {

    @Autowired
    private ClientRepositoryAdapter clientRepository;

    @Autowired
    private BitcoinUseCase getBitcoinUnitaryValueUseCase;

    @Autowired
    private OfferRepositoryAdapter offerRepository;

    public Offer createOffer(OfferRequest offerRequest, String apiKey) {
        Client client = clientRepository.findClient(apiKey);

        BigDecimal bitcoinUnitaryValue = getBitcoinUnitaryValueUseCase.getUnitaryValue(offerRequest.getOperation(), offerRequest.getQuotedOn());

        Offer offer = new Offer(offerRequest, apiKey, bitcoinUnitaryValue);

        client.hasBalanceForTransaction(offer);

        offerRepository.save(offer);

        return offer;
    }

    public Offer confirmOffer(String offerId, String apiKey) {
        Offer offer = offerRepository.getByOfferIdAndApiKey(offerId, apiKey);

        Client client = clientRepository.findClient(apiKey);

        client.confirmOffer(offer);

        clientRepository.save(client);

        return offerRepository.save(offer);
    }

}
