package org.brienze.biscoint.useCases;

import java.math.BigDecimal;

import org.brienze.biscoint.adapter.ClientRepositoryAdapter;
import org.brienze.biscoint.adapter.OfferRepositoryAdapter;
import org.brienze.biscoint.enums.Quote;
import org.brienze.biscoint.model.Client;
import org.brienze.biscoint.model.Offer;
import org.brienze.biscoint.model.OfferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateOfferUseCase {
	
	@Autowired
	private ClientRepositoryAdapter clientRepository;
	
	@Autowired
	private GetBitcoinUnitaryValueUseCase getBitcoinUnitaryValueUseCase;
	
	@Autowired
	private OfferRepositoryAdapter offerRepository;
	
	public Offer createOffer(OfferRequest offerRequest, String apiKey) {
		Client client = clientRepository.findClient(apiKey);
		
		BigDecimal bitcoinUnitaryValue = getBitcoinUnitaryValueUseCase.getvalue(offerRequest.getOperation(), Quote.BRL);
		
		Offer offer = new Offer(offerRequest, apiKey, bitcoinUnitaryValue);
		
		client.hasBalanceForTransaction(offer);
		
		offerRepository.save(offer);
		
		return offer;
	}

}
