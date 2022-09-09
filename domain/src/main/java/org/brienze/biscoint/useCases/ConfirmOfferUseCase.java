package org.brienze.biscoint.useCases;

import org.brienze.biscoint.adapter.ClientRepositoryAdapter;
import org.brienze.biscoint.adapter.OfferRepositoryAdapter;
import org.brienze.biscoint.model.Client;
import org.brienze.biscoint.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfirmOfferUseCase {

    @Autowired
    private OfferRepositoryAdapter offerRepository;

    @Autowired
    private ClientRepositoryAdapter clientRepository;

    public Offer confirmOffer(String offerId, String apiKey) {
        Offer offer = offerRepository.getByOfferIdAndApiKey(offerId, apiKey);

        Client client = clientRepository.findClient(apiKey);

        client.confirmOffer(offer);

        clientRepository.save(client);

        return offerRepository.save(offer);
    }

}
