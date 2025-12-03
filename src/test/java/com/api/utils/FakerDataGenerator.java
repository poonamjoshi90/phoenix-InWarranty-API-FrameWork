package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.xmlbeans.impl.store.Locale;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDataGenerator {

	private static Faker faker = new Faker();

	private static Random randam = new Random();
	public static  final int MST_SERVCE_LOCATION_ID = 0;
	public static int MST_PLATFORM_ID = 2;
	public static int MST_WARRENTLY_STATUS_ID = 1;
	public static int MST_ORM_ID = 1;
	public static int  PRODUCT_ID=1;
	public static int  MST_MODEL_ID=1;
	

	private FakerDataGenerator() {

	}


	public static CreateJobPayload generateFakeCreateJobData() {

		Customer customer = generateCustomerData();
		CustomerAddress customerAddress = generateCustomerAddressData();
		CustomerProduct customerProduct = generateCustomerProductData();
		List<Problems> problemsList = generateFakerProblemList();

		CreateJobPayload createJobPayload = new CreateJobPayload(MST_SERVCE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRENTLY_STATUS_ID, MST_ORM_ID, customer, customerAddress, customerProduct,
				problemsList);
		return createJobPayload;
	}

	public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {
 
		List<CreateJobPayload> createJobPayloadList= new  ArrayList<CreateJobPayload>();
		
		for(int i=1;i<=count;i++)
		{
		Customer customer = generateCustomerData();
		CustomerAddress customerAddress = generateCustomerAddressData();
		CustomerProduct customerProduct = generateCustomerProductData();
		List<Problems> problemsList = generateFakerProblemList();

		CreateJobPayload createJobPayload = new CreateJobPayload(MST_SERVCE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRENTLY_STATUS_ID, MST_ORM_ID, customer, customerAddress, customerProduct,
				problemsList);
		
		createJobPayloadList.add(createJobPayload);
		}
		return createJobPayloadList.iterator();
	}


	private static Customer generateCustomerData() {

		String firstName = faker.name().firstName();
		String LastName = faker.name().lastName();
		String mobilenumber = faker.numerify("909#######");
		String altmobilenumber = faker.numerify("909#######");
		String customeremailId = faker.internet().emailAddress();
		String altcustomeremailId = faker.internet().emailAddress();
		System.out.print(firstName);
		Customer customer = new Customer(firstName, LastName, mobilenumber, altmobilenumber, customeremailId,
				altcustomeremailId);

		return customer;
	}

	private static CustomerAddress generateCustomerAddressData() {
		String flatNumber = faker.address().buildingNumber();
		String apartmentName = faker.address().streetName();
		String streetname = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.numerify("909##");
		String country = faker.address().country();
		String state = faker.address().state();

		CustomerAddress customerAddress = new CustomerAddress(flatNumber, apartmentName, streetname, landmark, area,
				pincode, country, state);

		return customerAddress;
	}

	private static CustomerProduct generateCustomerProductData() {
		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		String imei1serialnumber = faker.numerify("202###########");
		String popurl = faker.internet().url();

		CustomerProduct customerProduct = new CustomerProduct(dop, imei1serialnumber, imei1serialnumber,
				imei1serialnumber, popurl, PRODUCT_ID, MST_MODEL_ID);
		return customerProduct;
	}

	private static List<Problems> generateFakerProblemList() {
		// TODO Auto-generated method stub String fakarremark=
		// faker.lorem().sentence(6);

		String fakarremark = faker.lorem().sentence(6);

		int problemId = randam.nextInt(28) + 1;

		Problems problems = new Problems(problemId, fakarremark);

		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problems);
		return problemsList;
	}

}
