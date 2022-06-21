package com.citta.card.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.citta.card.model.CardInfo;
import com.citta.card.repository.CardInfoRepository;

@Component
public class CardInfoDataLoaderOnStartUp {

	@Value("${cardinfo.file}")
	private String cardInfoFileName;
	
	@Autowired
	private CardInfoRepository repository;
	
	@EventListener(ApplicationReadyEvent.class)
	public void loadCardInfoDataAfterStartUp() {
		File file = new File(cardInfoFileName);
		try {
			List<CardInfo> cardInfos = ExcelHelper.excelToCardInfos(new FileInputStream(file));
			System.out.println("CardInfoDataLoaderOnStartUp: cardInfos size >> " + cardInfos.size());
			if(CollectionUtils.isNotEmpty(cardInfos)) {
				repository.deleteAll();
			}
			List<CardInfo> result = repository.saveAll(cardInfos);
			System.out.println("CardInfoDataLoaderOnStartUp: cardInfos size after saving >> " + result.size());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
