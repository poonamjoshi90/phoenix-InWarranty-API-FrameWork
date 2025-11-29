package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.poiji.bind.Poiji;

public class ExcelReaderUtilPoiji {

	private ExcelReaderUtilPoiji()
	{

	}

	public static <T>Iterator<T> loadtestData( String xlsxfile,  String sheetName,Class<T>classobj) {

		//"testData/LoginCredsExcelData.xlsx"
		//"LoginTestData"
		InputStream irs = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(xlsxfile);

		XSSFWorkbook myWorkbook = null;
		try {
			myWorkbook = new XSSFWorkbook(irs);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		XSSFSheet mysheet = myWorkbook.getSheet(sheetName);
	List<T> dataList	=Poiji.fromExcel(mysheet, classobj);
		return dataList.iterator();
	}
}
