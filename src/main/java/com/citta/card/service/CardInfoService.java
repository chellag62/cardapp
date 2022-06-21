package com.citta.card.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.citta.card.helper.ExcelHelper;
import com.citta.card.model.CardInfo;
import com.citta.card.repository.CardInfoRepository;

@Service
public class CardInfoService {
	
	@Autowired
	private CardInfoRepository cardInfoRepository;

	public void saveCardInfoFileData(MultipartFile file) {
		try {
			List<CardInfo> tutorials = ExcelHelper.excelToCardInfos(file.getInputStream());
			cardInfoRepository.saveAll(tutorials);
		}catch(IOException ie) {
			throw new RuntimeException("fail to store excel data: " + ie.getMessage());
		}
	}
}
