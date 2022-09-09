package org.brienze.biscoint.adapter;

import org.brienze.biscoint.model.Offer;

public interface OfferRepositoryAdapter {

	Offer save(Offer offer);

	Offer getByOfferIdAndApiKey(String offerId, String apiKey);

}
