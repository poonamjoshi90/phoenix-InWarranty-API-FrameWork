package com.api.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelReaderUtil {

	public static void main(String[] args) throws IOException {
	
InputStream is=Thread.currentThread().getContextClassLoader().getSystemResourceAsStream("testData/LoginCredsExcelData.xlsx");

 // XSSFWorkbook myWorkBook= new XSSFWorkBook(is);
 XSSFWorkbook myWorkbook= new XSSFWorkbook(is);

 
 XSSFSheet mysheet=myWorkbook.getSheet("LoginTestData");
 XSSFRow myRow;
 XSSFCell mycell;
 
 int lastRowIndex=mysheet.getLastRowNum();
 XSSFRow rowheader=mysheet.getRow(0);
 int lastIndexOfCol= rowheader.getLastCellNum()-1;
 //System.out.print(mycell);
 
  for(int rowindex=0;rowindex<lastRowIndex;rowindex++)
  {
	  for(int colindex=0;lastIndexOfCol<colindex;colindex++)
	  {
		  myRow=mysheet.getRow(rowindex);
		  mycell=myRow.getCell(colindex);
		  System.out.print(mycell + " ");
	  }
  }
  System.out.print("");
  
				

	}

}
