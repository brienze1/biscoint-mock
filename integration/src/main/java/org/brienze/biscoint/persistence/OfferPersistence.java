package org.brienze.biscoint.persistence;

import org.brienze.biscoint.adapter.OfferRepositoryAdapter;
import org.brienze.biscoint.entity.OfferEntity;
import org.brienze.biscoint.model.Offer;
import org.brienze.biscoint.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfferPersistence implements OfferRepositoryAdapter {

	@Autowired
	private OfferRepository offerRepository;
	
	@Override
	public Offer save(Offer offer) {
		return offerRepository.save(new OfferEntity(offer)).toOffer();
	}

	@Override
	public Offer getByOfferIdAndApiKey(String offerId, String apiKey) {
		return offerRepository.findByOfferIdAndApiKeyId(offerId, apiKey).toOffer();
	}

}
