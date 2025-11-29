package com.api.utils;

import java.util.Iterator;

import com.dataProvider.api.beans.CreateJobBean;

public class ExcelReaderUtilPoiji2 {


	public static void main ( String[] arg) {

		//"testData/LoginCredsExcelData.xlsx"
		//"LoginTestData"

		Iterator<CreateJobBean>iterator=ExcelReaderUtilPoiji.loadtestData( "testData/LoginCredsExcelData.xlsx", "JobCreateTestData",CreateJobBean.class);
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
		}

	}
}
