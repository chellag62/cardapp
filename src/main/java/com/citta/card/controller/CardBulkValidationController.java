package com.citta.card.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.citta.card.domain.CardBean;
import com.citta.card.helper.ExcelHelper;
import com.citta.card.service.CardValidationService;

@RestController
public class CardBulkValidationController {
	
	@Autowired
	CardValidationService cardValidationService;

	@RequestMapping(value={"validate/cards"}, method = { RequestMethod.POST }, 
			consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public List<CardBean> validateCreditCards(@RequestParam("file") MultipartFile file){
		InputStream inputStream = null;
		try {
			inputStream =  new BufferedInputStream(file.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> cardNumbers = ExcelHelper.excelToCardNumbers(inputStream);
		return cardValidationService.validateMultipleCards(cardNumbers);
	}
}
