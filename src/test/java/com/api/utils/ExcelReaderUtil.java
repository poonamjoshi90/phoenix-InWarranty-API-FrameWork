package com.api.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelReaderUtil {

	public static void main(String[] args) throws IOException {

InputStream irs = Thread.currentThread().getContextClassLoader()
.getResourceAsStream("testData/LoginCredsExcelData.xlsx");

  XSSFWorkbook myWorkbook= new XSSFWorkbook(irs);


 XSSFSheet mysheet=myWorkbook.getSheet("LoginTestData");
 XSSFRow myRow;
 XSSFCell mycell;

 int lastRowIndex=mysheet.getLastRowNum();
 XSSFRow rowheader=mysheet.getRow(0);
 int lastIndexOfCol= rowheader.getLastCellNum()-1;


  for(int rowindex=0;rowindex<=lastRowIndex;rowindex++)
  {
	  for(int colindex=0;colindex<=lastIndexOfCol;colindex++)
	  {
		  myRow=mysheet.getRow(rowindex);
		  mycell=myRow.getCell(colindex);
		  System.out.print(mycell + " ");
	  }
	  System.out.println("");
  }




	}

}
