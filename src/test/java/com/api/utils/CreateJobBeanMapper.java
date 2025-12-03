package com.api.utils;

import java.util.ArrayList;
import java.util.List;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress12;
import com.api.request.model.CustomerProduct12;
import com.api.request.model.Problems12;
import com.dataProvider.api.beans.CreateJobBean;

public class CreateJobBeanMapper {


	CreateJobBeanMapper()
	{

	}

	public static CreateJobPayload mapper( CreateJobBean bean)
	{
		//mst_service_location_id
	int mstServiceLocationid	=Integer.parseInt(bean.getMst_service_location_id());
	int mstPlatformid	=Integer.parseInt(bean.getMst_platform_id());
	int mstWarrentystatusid	=Integer.parseInt(bean.getMst_warrenty_status_id());
	int mstOemid	=Integer.parseInt(bean.getMst_oem_id());

	Customer customer=new Customer(bean.getCustomer__first_name(),
			bean.getCustomer__last_name(),
			bean.getCustomer__mobile_number(),
			bean.getCustomer__mobile_number_alt(),
			bean.getCustomer__email_id(),
			bean.getCustomer__email_id_alt());

	CustomerAddress12 customerAddress= new CustomerAddress12(bean.getCustomer_address__flat_number(),
			                           bean.getCustomer_address__apartment_name(),
			                           bean.getCustomer_address__street_name(),
			                           bean.getCustomer_address__landmark(),
			                           bean.getCustomer_address__area(),

			                           bean.getCustomer_address__pincode(),
			                           bean.getCustomer_address__country(),
			                           bean.getCustomer_address__state()
			                         );

	CustomerProduct12 customerProduct= new CustomerProduct12(bean.getCustomer_product__dop(),
			                         bean.getCustomer_product__serial_number(),
			                         bean.getCustomer_product__imei1(),
			                         bean.getCustomer_product__imei2(),
			                         bean.getCustomer_product__popurl(),
			                         Integer.parseInt( bean.getCustomer_product__product_id()),
			                         Integer.parseInt(bean.getCustomer_product__mst_model_id())
			                     );

	//Problems problems = new Problems(bean.getProblems__id(),
	//		                  bean.getProblems__remark());
	//List<Problems> problemList= new ArrayList<Problems>();



	List<Problems12> problemsList= new ArrayList<Problems12>();
	Problems12 problems = new Problems12(Integer.parseInt(bean.getProblems__id()),
			                     bean.getProblems__remark());
	problemsList.add(problems);
		CreateJobPayload payload= new CreateJobPayload(mstServiceLocationid, mstPlatformid, mstWarrentystatusid, mstOemid, customer, customerAddress, customerProduct, problemsList);
		return payload;

		}



}
