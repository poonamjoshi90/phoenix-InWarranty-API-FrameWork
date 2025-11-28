package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.UserCredentials;
import com.dataProvider.api.beans.UserBean;
import com.poiji.bind.Poiji;

public class ExcelReaderUtilPoiji {
	
	private ExcelReaderUtilPoiji()
	{
		
	}

	public static Iterator<UserBean> loadtestData( ) {

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
	List<UserBean> dataList	=Poiji.fromExcel(mysheet, UserBean.class);
		return dataList.iterator();
	}	
}
