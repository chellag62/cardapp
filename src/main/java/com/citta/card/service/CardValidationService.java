package com.citta.card.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citta.card.domain.CardBean;
import com.citta.card.model.BlackListCountry;
import com.citta.card.model.CardInfo;
import com.citta.card.model.ValidCard;
import com.citta.card.repository.BlackListCountryRepository;
import com.citta.card.repository.CardInfoRepository;
import com.citta.card.repository.ValidCardRepository;
import com.citta.card.util.Constants;

@Service
public class CardValidationService {

	@Autowired
	private CardInfoRepository cardInfoRepo;
	
	@Autowired
	BlackListCountryRepository blackListRepository;
	
	@Autowired
	ValidCardRepository validCardRepository;
	
	public List<CardBean> validateMultipleCards(List<String> cardNumbers) {
		List<CardBean> cardBeans = new ArrayList<CardBean>();
		for(String cardNumber: cardNumbers) {
			cardBeans.add(validateCreditCard(cardNumber));	
		}
		return cardBeans;
	}
	
	public CardBean validateCreditCard(String cardNumber) {
		CardBean cardBean = new CardBean();
		String binNumber = cardNumber.substring(0, 6);
		
		//Get country code
		CardInfo cardInfo = cardInfoRepo.findByBinNumber(Long.valueOf(binNumber));
		String countryCode = cardInfo.getCountryCode();
		
		cardBean.setCardNumber(cardNumber);
		//Validate country code for banned country
		BlackListCountry bannedCountry = blackListRepository.findByCode(countryCode);
		
		cardBean.setCardCountry(countryCode);
		if(bannedCountry != null && bannedCountry.getCode().equals(countryCode)) {
			cardBean.setCardCountryStatus(Constants.COUNTRY_STATUS_BANNED);
			cardBean.setCardStatus(Constants.CARD_STATUS_REJECTED);
			
			//Save card detail to db
			ValidCard validCard = new ValidCard();
			validCard.setCardNumber(cardNumber);
			validCard.setCountryCode(countryCode);
			validCardRepository.save(validCard);
		}else {
			cardBean.setCardCountryStatus(Constants.COUNTRY_STATUS_VALID);
			cardBean.setCardStatus(Constants.CARD_STATUS_VALID);
		}
		return cardBean;
	}
}
