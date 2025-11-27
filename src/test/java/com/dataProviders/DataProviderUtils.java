package com.dataProviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.testng.annotations.DataProvider;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredentials;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
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
	
}
