package com.api.utils;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
public class fakerDemo2 {

	public static void main(String[] args) {

		Faker faker = new Faker();
		
	String firstName=	faker.name().firstName();
	String LastName=	faker.name().lastName();
	String mobilenumber=	faker.numerify("909#######");
	String altmobilenumber=	faker.numerify("909#######");
	String customeremailId=	faker.internet().emailAddress();
	String altcustomeremailId=	faker.internet().emailAddress();
	System.out.print(firstName);
	Customer  customer = new Customer(firstName,LastName,mobilenumber,altmobilenumber,customeremailId,altcustomeremailId);
	
	 
	 String flatNumber= faker.address().buildingNumber();
	 String apartmentName= faker.address().streetName();
	 String streetname= faker.address().streetName();
	 String landmark= faker.address().streetName();
	 String area= faker.address().streetName();
	 String pincode= faker.numerify("909#######");
	 String country= faker.address().country();
	 String state= faker.address().state();
	 
	 
	 
	 CustomerAddress customerAddress= new CustomerAddress(flatNumber, apartmentName, streetname, landmark, area, pincode, country, state);
	 

	 
	 String dop= DateTimeUtil.getTimeWithDaysAgo(10);
	 String imei1serialnumber= faker.numerify("202#########");
	 String popurl=faker.internet().url();
	
	 
	 CustomerProduct customerProduct = new CustomerProduct(dop, imei1serialnumber, imei1serialnumber, imei1serialnumber, popurl, 1, 1);
	 
	 System.out.println(customerProduct);
	 String fakarremark= faker.lorem().sentence(6);
	 Random randam=new Random();
	int problemId= randam.nextInt(28)+1;
	 
	 Problems problems = new Problems(problemId, fakarremark);
	 
	 List<Problems> problemsList= new ArrayList<Problems>();
	 problemsList.add(problems);
	
	 
	 CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsList);
	 
	 System.out.println(createJobPayload);
	}
	

}
