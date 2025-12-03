package com.dataProviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredentials;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.ExcelReaderUtilPoiji;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JsonReaderUtil;
import com.dataProvider.api.beans.CreateJobBean;
import com.dataProvider.api.beans.UserBean;

public class DataProviderUtils {

	@DataProvider(name="LoginAPIDataProvider")
	public static Iterator<UserBean> loginAPIDataProvider()
	{
	  return CSVReaderUtil.loadCSV("testData/loginCredits.csv",UserBean.class);
	}

	@DataProvider(name="CreateJobAPIDataProvider",parallel=true)
	public static Iterator<CreateJobPayload> createJobDataProvider()
	{
	Iterator<CreateJobBean> CreateJobBeanIterator=	 CSVReaderUtil.loadCSV("testData/CreatedJobTestData.csv", CreateJobBean.class);

		 List<CreateJobPayload> payloadList= new ArrayList<CreateJobPayload>();
		 CreateJobBean tempBean;
		 CreateJobPayload tempPayload;
		 while(CreateJobBeanIterator.hasNext())
		 {
			 tempBean= CreateJobBeanIterator.next();
			 tempPayload=CreateJobBeanMapper.mapper(tempBean);
			 payloadList.add(tempPayload);
		 }
		 return payloadList.iterator();
	}

	@DataProvider(name="LoginAPIJSONDataProvider", parallel=true)
	public static Iterator<UserCredentials> loginAPIJSONDataProvider()
	{
	  return	JsonReaderUtil.loadJSON("testData/loginData.json",UserCredentials[].class);
	}

	@DataProvider(name="CreateJobAPIJsonDataProvider", parallel=true)
	public static Iterator<CreateJobPayload> createJobAPIJsonDataProvider()
	{
	  return	JsonReaderUtil.loadJSON("testData/CreateJobAPITestData.json",CreateJobPayload[].class);
	}

	@DataProvider(name="LoginAPIExcelDataProvider", parallel=true)
	public static Iterator<UserBean> loginAPIExcelDataProvider()
	{
		return ExcelReaderUtilPoiji.loadtestData("testData/LoginCredsExcelData.xlsx", "LoginTestData",UserBean.class);
	  	}
	
	@DataProvider(name="CreateJobAPIExcelDataProvider", parallel=true)
	public static Iterator<CreateJobPayload> createJobAPIExcelDataProvider()
	{
		Iterator<CreateJobBean>createJobBeanIterator=ExcelReaderUtilPoiji.loadtestData( "testData/LoginCredsExcelData.xlsx", "JobCreateTestData",CreateJobBean.class);
		 List<CreateJobPayload> payloadList= new ArrayList<CreateJobPayload>();
		 CreateJobBean tempBean;
		 CreateJobPayload tempPayload;
		 while(createJobBeanIterator.hasNext())
		 {
			 tempBean= createJobBeanIterator.next();
			 tempPayload=CreateJobBeanMapper.mapper(tempBean);
			 payloadList.add(tempPayload);
			 System.out.print("heloo");
		 }
		 return payloadList.iterator();
		//return ExcelReaderUtilPoiji.loadtestData("testData/LoginCredsExcelData.xlsx", "LoginTestData",UserBean.class);
	  	}
	
	@DataProvider(name="CreateJobAPIFakerDataProvider", parallel=true)
	public static Iterator<CreateJobPayload> createJobAPIFakerDataProvider()
	{
		Iterator<CreateJobPayload> payloadIterator=FakerDataGenerator.generateFakeCreateJobData(10);
		return payloadIterator;
	 	}


}
