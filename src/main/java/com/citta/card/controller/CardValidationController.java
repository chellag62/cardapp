package com.citta.card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citta.card.domain.CardBean;
import com.citta.card.service.CardValidationService;

@RestController
public class CardValidationController {
	
	@Autowired
	CardValidationService cardValidationService;

	@RequestMapping(value={"validate/card"}, method = { RequestMethod.POST })
	public CardBean validateCreditCard (@RequestParam String cardNumber) {
		return cardValidationService.validateCreditCard(cardNumber);
	}
}
