package com.api.testDriven;

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
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.DateTimeUtil;
import static com.api.utils.SpecUtil.*;
import com.github.fge.jsonschema.examples.Utils;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static  io.restassured.RestAssured.*;

public class CreateJobAPIDataDrivenTest {
	
	CreateJobPayload createJobPayload;
	
	
	@Test(description="Verify if create api is able to create job",
			dataProviderClass=com.dataProviders.DataProviderUtils.class,
					groups= {"api","regression","smoke"},
			dataProvider="CreateJobAPIDataProvider")
	public void createJobAPITest(CreateJobPayload createJobPayload)
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
