package com.citta.card.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.citta.card.model.CardInfo;

public class ExcelHelper {
  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  static String[] HEADERs = { "bin", "brand", "type", "issuer", "country_code", "country_name", "banned" };
  static String SHEET = "CardInfo";
  static String CARD_SHEET = "CardNumbers";

  public static boolean hasExcelFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

/*
  public static ByteArrayInputStream tutorialsToExcel(List<Tutorial> tutorials) {

    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
      Sheet sheet = workbook.createSheet(SHEET);

      // Header
      Row headerRow = sheet.createRow(0);

      for (int col = 0; col < HEADERs.length; col++) {
        Cell cell = headerRow.createCell(col);
        cell.setCellValue(HEADERs[col]);
      }

      int rowIdx = 1;
      for (Tutorial tutorial : tutorials) {
        Row row = sheet.createRow(rowIdx++);

        row.createCell(0).setCellValue(tutorial.getId());
        row.createCell(1).setCellValue(tutorial.getTitle());
        row.createCell(2).setCellValue(tutorial.getDescription());
        row.createCell(3).setCellValue(tutorial.isPublished());
      }

      workbook.write(out);
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
    }
  }*/

  public static List<CardInfo> excelToCardInfos(InputStream is) {
    try {
      Workbook workbook = new XSSFWorkbook(is);

      Sheet sheet = workbook.getSheet(SHEET);
      Iterator<Row> rows = sheet.iterator();

      List<CardInfo> cardInfos = new ArrayList<CardInfo>();

      int rowNumber = 0;
      while (rows.hasNext()) {
        Row currentRow = rows.next();

        // skip header
        if (rowNumber == 0) {
          rowNumber++;
          continue;
        }

        Iterator<Cell> cellsInRow = currentRow.iterator();

        CardInfo cardInfo = new CardInfo();

        int cellIdx = 0;
        while (cellsInRow.hasNext()) {
          Cell currentCell = cellsInRow.next();

          switch (cellIdx) {
          case 0:
            cardInfo.setBinNumber((long)currentCell.getNumericCellValue());
            break;

          case 1:
        	  cardInfo.setBrand(currentCell.getStringCellValue());
            break;

          case 2:
        	  cardInfo.setType(currentCell.getStringCellValue());
            break;

          case 3:
        	  cardInfo.setIssuer(currentCell.getStringCellValue());
            break;

          case 4:
        	  cardInfo.setCountryCode(currentCell.getStringCellValue());
            break;

//          case 5:
//        	  cardInfo.setCountryName(currentCell.getStringCellValue());
//            break;

          case 5:
          {
        	  String val = currentCell.getStringCellValue();
        	  boolean flag = !StringUtils.isEmpty(val) && 
        			  	val.equals("yes") ? true: false;
          	  System.out.println("flag >> " + flag);
          	  cardInfo.setBlackListFlag(flag) ;
        	  
          }
            break;

          default:
            break;
          }

          cellIdx++;
        }

        cardInfos.add(cardInfo);
        System.out.println(cardInfo.getBinNumber());
      }

      workbook.close();

      return cardInfos;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
    }
  }
  
  public static List<String> excelToCardNumbers(InputStream is) {
	    try {
	      Workbook workbook = new XSSFWorkbook(is);

	      Sheet sheet = workbook.getSheet(CARD_SHEET);
	      Iterator<Row> rows = sheet.iterator();

	      List<String> cardNumbers = new ArrayList<String>();

	      int rowNumber = 0;
	      while (rows.hasNext()) {
	        Row currentRow = rows.next();

	        // skip header
	        if (rowNumber == 0) {
	          rowNumber++;
	          continue;
	        }

	        Iterator<Cell> cellsInRow = currentRow.iterator();

	        CardInfo cardInfo = new CardInfo();

	        int cellIdx = 0;
	        while (cellsInRow.hasNext()) {
	          Cell currentCell = cellsInRow.next();

	          switch (cellIdx) {
	          	case 0:
	          		currentCell.setCellType(CellType.STRING);
	          		cardNumbers.add(currentCell.getStringCellValue());
	          		//cardNumbers.add(currentCell.getStringCellValue());
	          		break;

	          	default:
	          		break;
	          }

	          cellIdx++;
	        }
	        
	      }

	      workbook.close();

	      return cardNumbers;
	    } catch (IOException e) {
	      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
	    }
	  }

}

