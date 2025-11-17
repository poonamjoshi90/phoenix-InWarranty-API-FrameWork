package com.api.test;

import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.api.constant.Model;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Roles;
import static com.api.constant.Roles.*;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.DateTimeUtil;
import static com.api.utils.SpecUtil.*;
import com.github.fge.jsonschema.examples.Utils;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static  io.restassured.RestAssured.*;

public class CreateJobAPITest {
	
	CreateJobPayload createJobPayload;
	
	@BeforeMethod(description="Creating create Job API payload with FD user",groups= {"api","regression","smoke"})
	public void setup()
	{
	Customer customer =new Customer("Poonam", "Joshi", "9099091290", "", "test@mail.com", "");
	CustomerAddress customeraddress = new CustomerAddress("Flat-No-967", "ABC","Test123", "Test12", "TestArea12", "92122","India", "Delhi");
	CustomerProduct customer_product = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), "909909909901001", "909909909901001", "909909909901001", DateTimeUtil.getTimeWithDaysAgo(10), Product.Nexus_2.getCode(), Model.GALLEXY.getCode());
	Problems problems = new Problems(Problem.POOR_BATTERY_LIFE.getCode(),"Batary Issue");
	List<Problems> problemList= new ArrayList<Problems>();
	problemList.add(problems);
	
	 createJobPayload= new CreateJobPayload(0, 2, 1, 1, customer, customeraddress, customer_product, problemList);
	

	}
	
	@Test(description="verify create job sucessfully with FD user",groups= {"api","regression","smoke"})

	public void createJobAPITest()
	{
		given()
		.spec(RequestSpecWIthAuth(FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(responsespec_Ok())
		.and()
	      .body("message",equalTo("Job created successfully. "))
	      .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/CreateJobAPIResponseSchema.json"));
	     
		
	}

}
