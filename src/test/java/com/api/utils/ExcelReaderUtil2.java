package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.UserCredentials;

public class ExcelReaderUtil2 {

	private ExcelReaderUtil2()
	{

	}

	public static Iterator<UserCredentials> loadtestData( ) {

		InputStream irs = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testData/LoginCredsExcelData.xlsx");

		XSSFWorkbook myWorkbook = null;
		try {
			myWorkbook = new XSSFWorkbook(irs);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		XSSFSheet mysheet = myWorkbook.getSheet("LoginTestData");
		XSSFRow myRow;
		XSSFCell mycell;

		XSSFRow headerRow=mysheet.getRow(0);
		int userNameIndex=-1;
		int passwordIndex=-1;

		for(Cell cell :headerRow )
		{
			if(cell.getStringCellValue().trim().equalsIgnoreCase("username"))
			{
				userNameIndex=cell.getColumnIndex();
			}

			if(cell.getStringCellValue().trim().equalsIgnoreCase("password"))
			{
				passwordIndex=cell.getColumnIndex();
			}

		}
		System.out.println(userNameIndex + " "+passwordIndex);
		int lastRowIndex=mysheet.getLastRowNum();
		XSSFRow  rowData;
		UserCredentials userCredentials = null ;

		ArrayList<UserCredentials> userList=new ArrayList<UserCredentials>();
		for(int rowIndex=1;rowIndex<=lastRowIndex;rowIndex++)
		{
			rowData=mysheet.getRow(rowIndex);
			 userCredentials = new UserCredentials(rowData.getCell(userNameIndex).toString(),rowData.getCell(passwordIndex).toString());

			System.out.println(userCredentials);
			userList.add(userCredentials);
		}

		return userList.iterator();
	}

}
