package com.api.test;

import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;
import com.github.fge.jsonschema.examples.Utils;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static  io.restassured.RestAssured.*;

public class CreateJobAPITest {
	
	
	
	
	@Test
	public void createJobAPITest()
	{
		Customer customer =new Customer("Poonam", "Joshi", "9099091290", "", "test@mail.com", "");
		CustomerAddress customeraddress = new CustomerAddress("Flat-No-967", "ABC","Test123", "Test12", "TestArea12", "92122","India", "Delhi");
		CustomerProduct customer_product = new CustomerProduct("2025-10-07T18:30:00.000Z", "40129730786260", "40129730786260", "40129730786260", "2025-10-07T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1,"Batary Issue");
		List<Problems> problemList= new ArrayList<Problems>();
		problemList.add(problems);
		
		CreateJobPayload createJobPayload= new CreateJobPayload(0, 2, 1, 1, customer, customeraddress, customer_product, problemList);
		
		given()
		.spec(SpecUtil.RequestSpecWIthAuth(Roles.FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtil.responsespec_Ok())
		.and()
	      .body("message",equalTo("Job created successfully. "))
	      .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/CreateJobAPIResponseSchema.json"));
	     
		
	}

}
