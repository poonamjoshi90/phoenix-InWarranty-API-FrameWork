package com.api.utils;

import java.util.Iterator;

import com.dataProvider.api.beans.CreateJobBean;

public class demo {

	public static void main(String[] args) {

	//	CSVReaderUtil.loadCSV("testData/CreatedJobTestData.csv",CreateJobBean.class);
Iterator<CreateJobBean>	iterator =	CSVReaderUtil.loadCSV("testData/CreatedJobTestData.csv", CreateJobBean.class);

while(iterator.hasNext())
{
	System.out.println(iterator.next());
}


	}

}
